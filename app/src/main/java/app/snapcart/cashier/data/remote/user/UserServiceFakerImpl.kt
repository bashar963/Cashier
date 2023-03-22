package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.Language
import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.Response
import app.snapcart.cashier.utils.StatusSuccess
import kotlinx.coroutines.delay

class UserServiceFakerImpl : UserService {
    override suspend fun getProfile(): Response<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")

        return Response(user, StatusSuccess)
    }

    override suspend fun getProfileSettings(): Response<UserSetting> {
        delay(2000)
        val userSetting = UserSetting(true, Language.INDONESIAN)
        return Response(userSetting, StatusSuccess)
    }

    override suspend fun createProfile(name: String): Response<User> {
        delay(2000)
        val user = User(id = "1", name = "Bashar")
        return Response(user, StatusSuccess)
    }
}
