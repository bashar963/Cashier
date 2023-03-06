package app.snapcart.cashier.data.data_store

import app.snapcart.cashier.data.remote.AuthService
import app.snapcart.cashier.utils.ApiResult
import app.snapcart.cashier.utils.handleApiRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun login(): ApiResult<Any> =
        handleApiRequest { authService.login() }
}
