package app.snapcart.cashier.data.remote.user

import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.GrpcResponse

interface UserService {
    suspend fun getProfile(): GrpcResponse<User>
    suspend fun getProfileSettings(): GrpcResponse<UserSetting>
    suspend fun createProfile(name: String): GrpcResponse<User>
}
