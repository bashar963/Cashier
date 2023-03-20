package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.utils.GrpcResponse
import app.snapcart.cashier.utils.GrpcStatusSuccess
import kotlinx.coroutines.delay

class StoreServiceFakerImpl :
    StoreService {

    override suspend fun createStore(request: CreateStoreRequest): GrpcResponse<String> {
        delay(2000)
        return GrpcResponse(body = "1", GrpcStatusSuccess)
    }

    override suspend fun getOwnedStore(): GrpcResponse<Store> {
        delay(2000)
        return GrpcResponse(
            body = Store("", "", "", 1.0, 1.0, "", ""),
            status = GrpcStatusSuccess
        )
    }
}
