package app.snapcart.cashier.data.repo.store

import app.snapcart.cashier.data.data_store.StoreRemoteDataSource
import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepository
@Inject constructor(private val storeRemoteDataSource: StoreRemoteDataSource) {

    fun createStore(request: CreateStoreRequest): Flow<Result<String>> {
        return flow {

            val result = storeRemoteDataSource.createStore(request = request)

            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.isFailure
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getOwnedStore(): Flow<Result<Store>> {
        return flow {

            val result = storeRemoteDataSource.getOwnedStore()

            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.isFailure
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
