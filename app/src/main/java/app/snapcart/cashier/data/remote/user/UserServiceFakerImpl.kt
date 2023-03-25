package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.user.Language
import app.snapcart.cashier.data.models.user.User
import app.snapcart.cashier.data.models.user.UserSetting
import kotlinx.coroutines.delay

class UserServiceFakerImpl : UserService {
    override suspend fun getProfile(): Result<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")

        return Result.success(user)
    }

    override suspend fun getProfileSettings(): Result<UserSetting> {
        delay(2000)
        val userSetting = UserSetting(true, Language.INDONESIAN)
        return Result.success(userSetting)
    }

    override suspend fun createProfile(name: String): Result<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")
        return Result.success(user)
    }
}
