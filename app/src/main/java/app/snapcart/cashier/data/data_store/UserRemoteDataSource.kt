package app.snapcart.cashier.data.data_store

import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.data.remote.user.UserService
import app.snapcart.cashier.utils.handleApiRequest
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun getProfile(): Result<User> =
        handleApiRequest { userService.getProfile() }

    suspend fun getProfileSettings(): Result<UserSetting> =
        handleApiRequest { userService.getProfileSettings() }

    suspend fun createProfile(name: String): Result<User> =
        handleApiRequest { userService.createProfile(name) }
}
