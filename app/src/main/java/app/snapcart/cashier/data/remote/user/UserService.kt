package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.Response

interface UserService {
    suspend fun getProfile(): Response<User>
    suspend fun getProfileSettings(): Response<UserSetting>
    suspend fun createProfile(name: String): Response<User>
}
