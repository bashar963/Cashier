package app.snapcart.cashier.data.remote.auth

import app.snapcart.cashier.data.models.AuthServiceStub
import app.snapcart.cashier.data.models.OtpResponse
import app.snapcart.cashier.utils.Response
import app.snapcart.cashier.utils.Status
import app.snapcart.cashier.utils.StatusError
import app.snapcart.cashier.utils.StatusSuccess
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceGetOTPResponse
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPRequest
import com.snapcart.protos.api.common.v1.AuthServiceVerifyOTPResponse
import io.grpc.Metadata
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val authServiceGrpc: AuthServiceStub
) : AuthService {

    override suspend fun getOTP(phone: String): Response<OtpResponse> {
        val request = AuthServiceGetOTPRequest.newBuilder().setPhone(phone).build()
        val response = authServiceGrpc.getOTP(request, Metadata())

        val status: Status = when (response.status) {
            AuthServiceGetOTPResponse.Status.STATUS_UNSPECIFIED ->
                StatusError(message = AuthServiceGetOTPResponse.Status.STATUS_UNSPECIFIED)
            AuthServiceGetOTPResponse.Status.STATUS_SUCCESS -> StatusSuccess
            AuthServiceGetOTPResponse.Status.STATUS_FAILED ->
                StatusError(message = AuthServiceGetOTPResponse.Status.STATUS_FAILED)
            AuthServiceGetOTPResponse.Status.STATUS_WAIT ->
                StatusError(message = AuthServiceGetOTPResponse.Status.STATUS_WAIT)
            AuthServiceGetOTPResponse.Status.UNRECOGNIZED ->
                StatusError(message = AuthServiceGetOTPResponse.Status.UNRECOGNIZED)
            else -> StatusError(message = AuthServiceGetOTPResponse.Status.UNRECOGNIZED)
        }

        return Response(OtpResponse(response.message, response.retryAt.seconds) , status)
    }

    override suspend fun verifyOTP(otp: String): Response<String> {
        val request = AuthServiceVerifyOTPRequest.newBuilder().setOtp(otp).build()

        val response = authServiceGrpc.verifyOTP(request, Metadata())

        val status: Status = when (response.status) {
            AuthServiceVerifyOTPResponse.Status.STATUS_UNSPECIFIED ->
                StatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_UNSPECIFIED)
            AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_ALLOWED -> StatusSuccess
            AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_DENIED ->
                StatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_LOGIN_DENIED)
            AuthServiceVerifyOTPResponse.Status.STATUS_INCORRECT_OTP ->
                StatusError(message = AuthServiceVerifyOTPResponse.Status.STATUS_INCORRECT_OTP)
            AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED ->
                StatusError(message = AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED)
            else -> StatusError(message = AuthServiceVerifyOTPResponse.Status.UNRECOGNIZED)
        }
        return Response(response.message, status)
    }
}
