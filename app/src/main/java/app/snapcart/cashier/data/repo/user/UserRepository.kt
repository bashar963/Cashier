package app.snapcart.cashier.data.repo.user

import app.snapcart.cashier.data.data_store.UserRemoteDataSource
import app.snapcart.cashier.data.models.User
import app.snapcart.cashier.data.models.UserSetting
import app.snapcart.cashier.utils.ApiError
import app.snapcart.cashier.utils.ApiException
import app.snapcart.cashier.utils.ApiLoading
import app.snapcart.cashier.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    fun getProfile(): Flow<ApiResult<User>> {
        return flow {
            emit(ApiLoading())
            val result = userRemoteDataSource.getProfile()

            // in case of error
            if (result is ApiError) {
                // do something with it maybe
                result.status
            }
            if (result is ApiException) {
                // do something with it maybe
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun getProfileSettings(): Flow<ApiResult<UserSetting>> {
        return flow {
            emit(ApiLoading())
            val result = userRemoteDataSource.getProfileSettings()

            // in case of error
            if (result is ApiError) {
                // do something with it maybe
                result.status
            }
            if (result is ApiException) {
                // do something with it maybe
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun createProfile(name: String): Flow<ApiResult<User>> {
        return flow {
            emit(ApiLoading())
            val result = userRemoteDataSource.createProfile(name = name)

            // in case of error
            if (result is ApiError) {
                // do something with it maybe
                result.status
            }
            if (result is ApiException) {
                // do something with it maybe
                result.e
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}
