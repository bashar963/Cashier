package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.utils.GrpcResponse
import app.snapcart.cashier.utils.GrpcStatusSuccess
import kotlinx.coroutines.delay

class AuthServiceFakerImpl : AuthService {
    override suspend fun getOTP(phone: String): GrpcResponse<String> {
        println("logging phone $phone")
        delay(2000)
        return GrpcResponse("ok", GrpcStatusSuccess)
    }

    override suspend fun verifyOTP(otp: String): GrpcResponse<String> {
        println("verifying otp $otp")
        delay(2000)
        return GrpcResponse("ok", GrpcStatusSuccess)
    }
}
