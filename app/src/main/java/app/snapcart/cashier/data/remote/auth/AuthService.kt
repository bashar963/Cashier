package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.OtpResponse
import app.snapcart.cashier.utils.Response

interface AuthService {
    suspend fun getOTP(phone: String): Response<OtpResponse>
    suspend fun verifyOTP(otp: String): Response<String>
}
