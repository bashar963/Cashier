package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.utils.Response

interface StoreService {

    suspend fun createStore(request: CreateStoreRequest): Response<String>

    suspend fun getOwnedStore(): Response<Store>
}
