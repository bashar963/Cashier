package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.store.Store
import app.snapcart.cashier.data.models.StoreServiceStub
import app.snapcart.cashier.data.models.store.CreateStoreRequest
import com.google.protobuf.Empty
import com.snapcart.protos.api.cashier.v1.CashierApiServiceCreateStoreRequest
import javax.inject.Inject

class StoreServiceImpl
@Inject constructor(private val storeServiceGrpc: StoreServiceStub) :
    StoreService {

    override suspend fun createStore(request: CreateStoreRequest): Result<String> {
        val storeRequest = CashierApiServiceCreateStoreRequest.newBuilder()
        storeRequest.storeName = request.storeName
        storeRequest.ownerName = request.ownerName
        storeRequest.address = request.address
        storeRequest.lat = request.lat
        storeRequest.lng = request.lng
        storeRequest.insidePhoto = request.insidePhoto
        storeRequest.outsidePhoto = request.outsidePhoto

        val response = storeServiceGrpc.createStore(storeRequest.build())

        return Result.success(response.id)
    }

    override suspend fun getOwnedStore(): Result<Store> {
        val response = storeServiceGrpc.getOwnedStore(Empty.getDefaultInstance())

        val store = Store(
            storeName = response.storeName,
            ownerName = response.ownerName,
            address = response.address,
            lat = response.lat,
            lng = response.lng,
            insidePhoto = response.insidePhoto,
            outsidePhoto = response.outsidePhoto
        )

        return Result.success(store)
    }
}
