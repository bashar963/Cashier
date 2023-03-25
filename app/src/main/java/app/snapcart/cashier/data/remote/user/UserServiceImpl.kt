package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.user.Language
import app.snapcart.cashier.data.models.user.User
import app.snapcart.cashier.data.models.UserServiceStub
import app.snapcart.cashier.data.models.user.UserSetting
import com.google.protobuf.Empty
import com.snapcart.protos.api.cashier.v1.CashierApiServiceCreateProfileRequest
import com.snapcart.protos.api.cashier.v1.CashierApiServiceLanguage
import io.grpc.Metadata
import javax.inject.Inject

class UserServiceImpl
@Inject constructor(
    private val userServiceGrpc: UserServiceStub
) : UserService {

    override suspend fun getProfile(): Result<User> {
        val response = userServiceGrpc.getProfile(Empty.getDefaultInstance(), Metadata())
        val user = User(id = response.id, name = response.name)

        return Result.success(user)
    }

    override suspend fun getProfileSettings(): Result<UserSetting> {
        val response = userServiceGrpc.getProfileSettings(Empty.getDefaultInstance(), Metadata())
        val language = when (response.language) {
            CashierApiServiceLanguage.LANGUAGE_UNSPECIFIED -> Language.INDONESIAN
            CashierApiServiceLanguage.LANGUAGE_EN -> Language.ENGLISH
            CashierApiServiceLanguage.LANGUAGE_ID -> Language.INDONESIAN
            CashierApiServiceLanguage.UNRECOGNIZED -> Language.INDONESIAN
            else -> Language.INDONESIAN
        }
        val userSetting = UserSetting(response.notificationsEnabled, language)
        return Result.success(userSetting)
    }

    override suspend fun createProfile(name: String): Result<User> {
        val request = CashierApiServiceCreateProfileRequest.newBuilder().setName(name).build()
        val response = userServiceGrpc.createProfile(request, Metadata())
        val user = User(id = response.id, name = response.name)

        return Result.success(user)
    }
}
