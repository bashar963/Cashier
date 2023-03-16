package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.utils.GrpcResponse

interface AuthService {
    suspend fun getOTP(phone: String): GrpcResponse
    suspend fun verifyOTP(otp: String): GrpcResponse
}
