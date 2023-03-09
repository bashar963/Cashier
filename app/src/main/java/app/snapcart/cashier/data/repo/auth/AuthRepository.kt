package app.snapcart.cashier.data.repo.auth

import app.snapcart.cashier.data.data_store.AuthRemoteDataSource
import app.snapcart.cashier.utils.ApiError
import app.snapcart.cashier.utils.ApiException
import app.snapcart.cashier.utils.ApiLoading
import app.snapcart.cashier.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) {
    fun login(): Flow<ApiResult<Any>> {
        return flow {
            emit(ApiLoading())
            val result = authRemoteDataSource.login()

            // in case of error
            if (result is ApiError) {
                result.message
            }
            if (result is ApiException) {
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
