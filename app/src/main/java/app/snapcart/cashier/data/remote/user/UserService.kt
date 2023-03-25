package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.user.User
import app.snapcart.cashier.data.models.user.UserSetting

interface UserService {
    suspend fun getProfile(): Result<User>
    suspend fun getProfileSettings(): Result<UserSetting>
    suspend fun createProfile(name: String): Result<User>
}
