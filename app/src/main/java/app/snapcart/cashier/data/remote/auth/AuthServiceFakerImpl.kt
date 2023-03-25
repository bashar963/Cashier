package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.auth.OtpResponse
import app.snapcart.cashier.data.models.auth.VerifyOtpResponse
import kotlinx.coroutines.delay

class AuthServiceFakerImpl : AuthService {
    override suspend fun getOTP(phone: String): Result<OtpResponse> {
        println("logging phone $phone")
        delay(2000)
        return Result.success(OtpResponse("ok", 30L, 6))
    }

    override suspend fun verifyOTP(otp: String): Result<VerifyOtpResponse> {
        println("verifying otp $otp")
        delay(2000)
        return Result.success(
            VerifyOtpResponse(
                "Success",
                "token",
                "refresh token",
                "12"
            )
        )
    }
}
