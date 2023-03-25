package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.auth.OtpResponse
import app.snapcart.cashier.data.models.auth.VerifyOtpResponse

interface AuthService {
    suspend fun getOTP(phone: String): Result<OtpResponse>
    suspend fun verifyOTP(otp: String): Result<VerifyOtpResponse>
}
