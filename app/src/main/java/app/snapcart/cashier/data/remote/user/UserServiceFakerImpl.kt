package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.Language
import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.GrpcResponse
import app.snapcart.cashier.utils.GrpcStatusSuccess
import kotlinx.coroutines.delay

class UserServiceFakerImpl : UserService {
    override suspend fun getProfile(): GrpcResponse<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")

        return GrpcResponse(user, GrpcStatusSuccess)
    }

    override suspend fun getProfileSettings(): GrpcResponse<UserSetting> {
        delay(2000)
        val userSetting = UserSetting(true, Language.INDONESIAN)
        return GrpcResponse(userSetting, GrpcStatusSuccess)
    }

    override suspend fun createProfile(name: String): GrpcResponse<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")
        return GrpcResponse(user, GrpcStatusSuccess)
    }
}
