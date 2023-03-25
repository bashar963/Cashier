package app.snapcart.cashier.data.repo.auth

import app.snapcart.cashier.data.models.auth.OtpResponse
import app.snapcart.cashier.data.models.auth.VerifyOtpResponse
import app.snapcart.cashier.data.remote.auth.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun getOtp(phone: String): Result<OtpResponse> {
        return authService.getOTP(phone = phone)
    }

    fun verifyOTP(otp: String): Flow<Result<VerifyOtpResponse>> {
        return flow {
            val result = authService.verifyOTP(otp = otp)

            // in case of success cache access token
            if (result.isSuccess) {
                // do something with it maybe
                result.apply {
                }
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
