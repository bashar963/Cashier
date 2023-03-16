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
    fun getOTP(phone: String): Flow<ApiResult<String>> {
        return flow {
            emit(ApiLoading())
            val result = authRemoteDataSource.getOTP(phone = phone)

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

    fun verifyOTP(otp: String): Flow<ApiResult<String>> {
        return flow {
            emit(ApiLoading())
            val result = authRemoteDataSource.verifyOTP(otp = otp)

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
