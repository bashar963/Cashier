package app.snapcart.cashier.data.data_store

import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.data.remote.store.StoreService
import app.snapcart.cashier.utils.handleApiRequest
import javax.inject.Inject

class StoreRemoteDataSource @Inject constructor(
    private val storeService: StoreService
) {

    suspend fun createStore(request: CreateStoreRequest): Result<String> =
        handleApiRequest { storeService.createStore(request = request) }

    suspend fun getOwnedStore(): Result<Store> =
        handleApiRequest { storeService.getOwnedStore() }
}
