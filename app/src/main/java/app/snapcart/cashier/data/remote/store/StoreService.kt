package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.utils.GrpcResponse

interface StoreService {

    suspend fun createStore(request: CreateStoreRequest): GrpcResponse<String>

    suspend fun getOwnedStore(): GrpcResponse<Store>
}
