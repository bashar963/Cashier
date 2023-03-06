package app.snapcart.cashier.data.remote

import retrofit2.Response
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun login(): Response<Any>
}
