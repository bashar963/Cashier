package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.AuthServiceStub
import app.snapcart.cashier.data.models.GeneralApiException
import app.snapcart.cashier.data.models.auth.IncorrectOTPExceptions
import app.snapcart.cashier.data.models.auth.LoginDeniedExceptions
import app.snapcart.cashier.data.models.auth.OtpResponse
import app.snapcart.cashier.data.models.auth.VerifyOtpResponse
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPResponse
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPResponse
import io.grpc.Metadata
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val authServiceGrpc: AuthServiceStub
) : AuthService {

    override suspend fun getOTP(phone: String): Result<OtpResponse> {
        val request = AuthServiceGetOTPRequest.newBuilder().setPhone(phone).build()
        val response = authServiceGrpc.getOTP(request, Metadata())
        if (response.status != AuthServiceGetOTPResponse.Status.STATUS_SUCCESS) {
            return Result.failure(GeneralApiException())
        }
        return Result.success(OtpResponse(response.message, response.retryAt.seconds, response.otpLength))
    }

    override suspend fun verifyOTP(otp: String): Result<VerifyOtpResponse> {
        val request = AuthServiceVerifyOTPRequest.newBuilder().setOtp(otp).build()
        val response = authServiceGrpc.verifyOTP(request, Metadata())

        if (response.status != AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_ALLOWED) {
            return when (response.status) {
                AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_DENIED ->
                    Result.failure(LoginDeniedExceptions())
                AuthServiceVerifyOTPResponse.Status.STATUS_INCORRECT_OTP ->
                    Result.failure(IncorrectOTPExceptions())
                else -> Result.failure(GeneralApiException())
            }
        }

        return Result.success(
            VerifyOtpResponse(
                response.message,
                response.accessToken,
                response.refreshToken,
                response.userId
            )
        )
    }
}
