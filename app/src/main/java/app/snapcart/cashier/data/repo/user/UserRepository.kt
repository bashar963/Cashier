package app.snapcart.cashier.data.repo.user

import app.snapcart.cashier.data.data_store.UserRemoteDataSource
import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    fun getProfile(): Flow<Result<User>> {
        return flow {
            val result = userRemoteDataSource.getProfile()
            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.isFailure
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getProfileSettings(): Flow<Result<UserSetting>> {
        return flow {
            val result = userRemoteDataSource.getProfileSettings()
            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.isFailure
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun createProfile(name: String): Flow<Result<User>> {
        return flow {
            val result = userRemoteDataSource.createProfile(name = name)
            // in case of error
            if (result.isFailure) {
                // do something with it maybe
                result.isFailure
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
