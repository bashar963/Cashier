package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.utils.GrpcResponse
import app.snapcart.cashier.utils.GrpcStatus
import app.snapcart.cashier.utils.GrpcStatusError
import app.snapcart.cashier.utils.GrpcStatusSuccess
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPResponse
import com.snapcart.protos.api.common.v1.AuthServiceGrpcKt
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPResponse
import io.grpc.Metadata
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val authServiceGrpc: AuthServiceGrpcKt.AuthServiceCoroutineStub
) : AuthService {

    override suspend fun getOTP(phone: String): GrpcResponse {
        val request = AuthServiceGetOTPRequest.newBuilder().setPhone(phone).build()
        val response = authServiceGrpc.getOTP(request, Metadata())

        val status: GrpcStatus = when (response.status) {
            AuthServiceGetOTPResponse.Status.STATUS_UNSPECIFIED -> GrpcStatusError(message = AuthServiceGetOTPResponse.Status.STATUS_UNSPECIFIED)
            AuthServiceGetOTPResponse.Status.STATUS_SUCCESS -> GrpcStatusSuccess
            AuthServiceGetOTPResponse.Status.STATUS_FAILED -> GrpcStatusError(message = AuthServiceGetOTPResponse.Status.STATUS_FAILED)
            AuthServiceGetOTPResponse.Status.STATUS_WAIT -> GrpcStatusError(message = AuthServiceGetOTPResponse.Status.STATUS_WAIT)
            AuthServiceGetOTPResponse.Status.UNRECOGNIZED -> GrpcStatusError(message = AuthServiceGetOTPResponse.Status.UNRECOGNIZED)
            else -> GrpcStatusError(message = AuthServiceGetOTPResponse.Status.UNRECOGNIZED)
        }
        return GrpcResponse(response.message, status)
    }

    override suspend fun verifyOTP(otp: String): GrpcResponse {
        val request = AuthServiceVerifyOTPRequest.newBuilder().setOtp(otp).build()

        val response = authServiceGrpc.verifyOTP(request, Metadata())

        val status: GrpcStatus = when (response.status) {
            AuthServiceVerifyOTPResponse.Status.STATUS_UNSPECIFIED -> GrpcStatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_UNSPECIFIED)
            AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_ALLOWED -> GrpcStatusSuccess
            AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_DENIED -> GrpcStatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_DENIED)
            AuthServiceVerifyOTPResponse.Status.STATUS_INCORRECT_OTP -> GrpcStatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_INCORRECT_OTP)
            AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED -> GrpcStatusError(message = AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED)
            else -> GrpcStatusError(message = AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED)
        }
        return GrpcResponse(response.message, status)
    }
}
