package app.snapcart.cashier.data.repo.store

import app.snapcart.cashier.data.models.store.Store
import app.snapcart.cashier.data.models.store.CreateStoreRequest
import app.snapcart.cashier.data.remote.store.StoreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepository
@Inject constructor(private val storeService: StoreService) {

    suspend fun createStore(request: CreateStoreRequest): Result<String> {
        return storeService.createStore(request = request)
    }

    fun getOwnedStore(): Flow<Result<Store>> {
        return flow {
            val result = storeService.getOwnedStore()

            // in case of success Cache the store
            if (result.isSuccess) {
                // do something with it maybe
                result.isSuccess
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
