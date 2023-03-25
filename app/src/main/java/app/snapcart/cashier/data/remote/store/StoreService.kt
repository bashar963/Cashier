package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.store.Store
import app.snapcart.cashier.data.models.store.CreateStoreRequest

interface StoreService {

    suspend fun createStore(request: CreateStoreRequest): Result<String>

    suspend fun getOwnedStore(): Result<Store>
}
