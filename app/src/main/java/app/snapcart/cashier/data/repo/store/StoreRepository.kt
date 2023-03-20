package app.snapcart.cashier.data.repo.store

import app.snapcart.cashier.data.data_store.StoreRemoteDataSource
import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.utils.ApiError
import app.snapcart.cashier.utils.ApiException
import app.snapcart.cashier.utils.ApiLoading
import app.snapcart.cashier.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreRepository
@Inject constructor(private val storeRemoteDataSource: StoreRemoteDataSource) {

    fun createStore(request: CreateStoreRequest): Flow<ApiResult<String>> {
        return flow {
            emit(ApiLoading())
            val result = storeRemoteDataSource.createStore(request = request)

            // in case of error
            if (result is ApiError) {
                // do something with it maybe
                result.status
            }
            if (result is ApiException) {
                // do something with it maybe
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getOwnedStore(): Flow<ApiResult<Store>> {
        return flow {
            emit(ApiLoading())
            val result = storeRemoteDataSource.getOwnedStore()

            // in case of error
            if (result is ApiError) {
                // do something with it maybe
                result.status
            }
            if (result is ApiException) {
                // do something with it maybe
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
