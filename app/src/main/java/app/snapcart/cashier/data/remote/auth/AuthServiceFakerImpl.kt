package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.OtpResponse
import app.snapcart.cashier.utils.Response
import app.snapcart.cashier.utils.StatusSuccess
import kotlinx.coroutines.delay

class AuthServiceFakerImpl : AuthService {
    override suspend fun getOTP(phone: String): Response<OtpResponse> {
        println("logging phone $phone")
        delay(2000)
        return Response(OtpResponse("ok", 30L), StatusSuccess)
    }

    override suspend fun verifyOTP(otp: String): Response<String> {
        println("verifying otp $otp")
        delay(2000)
        return Response("ok", StatusSuccess)
    }
}
