package app.snapcart.cashier.data.repo.user

import app.snapcart.cashier.data.models.user.User
import app.snapcart.cashier.data.models.user.UserSetting
import app.snapcart.cashier.data.remote.user.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    fun getProfile(): Flow<Result<User>> {
        return flow {
            val result = userService.getProfile()
            if (result.isSuccess) {
                // do something with it maybe
                result.getOrNull()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getProfileSettings(): Flow<Result<UserSetting>> {
        return flow {
            val result = userService.getProfileSettings()

            if (result.isSuccess) {
                // do something with it maybe
                result.getOrNull()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun createProfile(name: String): Flow<Result<User>> {
        return flow {
            val result = userService.createProfile(name = name)

            if (result.isSuccess) {
                // do something with it maybe
                result.getOrNull()
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
