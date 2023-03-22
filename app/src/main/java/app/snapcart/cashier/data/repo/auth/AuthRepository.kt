package app.snapcart.cashier.data.repo.auth

import app.snapcart.cashier.data.data_store.AuthRemoteDataSource
import app.snapcart.cashier.data.models.OtpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) {
    fun getOTP(phone: String): Flow<Result<OtpResponse>> {
        return flow {
            val result = authRemoteDataSource.getOTP(phone = phone)
            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.apply {
                }
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun verifyOTP(otp: String): Flow<Result<String>> {
        return flow {
            val result = authRemoteDataSource.verifyOTP(otp = otp)

            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.apply {
                }
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
