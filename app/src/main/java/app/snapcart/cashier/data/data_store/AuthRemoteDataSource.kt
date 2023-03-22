package app.snapcart.cashier.data.data_store

import app.snapcart.cashier.data.models.OtpResponse
import app.snapcart.cashier.data.remote.auth.AuthService
import app.snapcart.cashier.utils.handleApiRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun getOTP(phone: String): Result<OtpResponse> =
        handleApiRequest { authService.getOTP(phone = phone) }

    suspend fun verifyOTP(otp: String): Result<String> =
        handleApiRequest { authService.verifyOTP(otp = otp) }
}
