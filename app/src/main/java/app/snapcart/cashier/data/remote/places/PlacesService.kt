package app.snapcart.cashier.data.remote.places

import app.snapcart.cashier.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesService {

    @GET("/{address}")
    suspend fun fetchAddresses(
        @Path("address") query:String,
        @Query("json") json:String = "1",
        @Query("scantext") scanText:String = "1",
        @Query("region") region:String = "ID",
        @Query("auth") auth:String = BuildConfig.GEO_EXTERNAL_API_KEY,
    ) : Response<Any>
}