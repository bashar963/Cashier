package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.Language
import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserServiceStub
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.Response
import app.snapcart.cashier.utils.StatusSuccess
import com.google.protobuf.Empty
import com.snapcart.protos.api.cashier.v1.CashierApiServiceCreateProfileRequest
import com.snapcart.protos.api.cashier.v1.CashierApiServiceLanguage
import io.grpc.Metadata
import javax.inject.Inject

class UserServiceImpl
@Inject constructor(
    private val userServiceGrpc: UserServiceStub
) : UserService {

    override suspend fun getProfile(): Response<User> {
        val response = userServiceGrpc.getProfile(Empty.getDefaultInstance(), Metadata())
        val user = User(id = response.id, name = response.name)

        return Response(user, StatusSuccess)
    }

    override suspend fun getProfileSettings(): Response<UserSetting> {
        val response = userServiceGrpc.getProfileSettings(Empty.getDefaultInstance(), Metadata())
        val language = when (response.language) {
            CashierApiServiceLanguage.LANGUAGE_UNSPECIFIED -> Language.INDONESIAN
            CashierApiServiceLanguage.LANGUAGE_EN -> Language.ENGLISH
            CashierApiServiceLanguage.LANGUAGE_ID -> Language.INDONESIAN
            CashierApiServiceLanguage.UNRECOGNIZED -> Language.INDONESIAN
            else -> Language.INDONESIAN
        }
        val userSetting = UserSetting(response.notificationsEnabled, language)
        return Response(userSetting, StatusSuccess)
    }

    override suspend fun createProfile(name: String): Response<User> {
        val request = CashierApiServiceCreateProfileRequest.newBuilder().setName(name).build()
        val response = userServiceGrpc.createProfile(request, Metadata())
        val user = User(id = response.id, name = response.name)

        return Response(user, StatusSuccess)
    }
}
