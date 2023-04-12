package app.snapcart.cashier.di

import app.snapcart.cashier.BuildConfig
import app.snapcart.cashier.data.remote.places.PlacesServiceImpl
import app.snapcart.cashier.data.remote.auth.AuthService
import app.snapcart.cashier.data.remote.auth.AuthServiceFakerImpl
import app.snapcart.cashier.data.remote.places.PlacesService
import app.snapcart.cashier.data.remote.places.PlacesServiceHelper
import app.snapcart.cashier.data.remote.store.StoreService
import app.snapcart.cashier.data.remote.store.StoreServiceFakerImpl
import app.snapcart.cashier.data.remote.user.UserService
import app.snapcart.cashier.data.remote.user.UserServiceFakerImpl
import app.snapcart.cashier.utils.CashierResponseClientInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .apply {
                if (BuildConfig.HTTP_LOGGING_ENABLED) {
                    addInterceptor(loggingInterceptor)
                }
            }
            // Add more interceptor here
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGeoRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GEO_EXTERNAL_API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun providePlacesService(retrofit: Retrofit): PlacesService = retrofit.create(PlacesService::class.java)

    @Singleton
    @Provides
    fun provideManagedChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            BuildConfig.API_URL,
            BuildConfig.API_HOST.toInt(),
        ).apply {
            idleTimeout(30, TimeUnit.SECONDS)
            usePlaintext()
            if (BuildConfig.HTTP_LOGGING_ENABLED) {
                intercept(CashierResponseClientInterceptor())
            }
        }
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthService(): AuthService =
        AuthServiceFakerImpl()

    @Singleton
    @Provides
    fun provideUserService(): UserService =
        UserServiceFakerImpl()

    @Singleton
    @Provides
    fun provideStoreService(): StoreService =
        StoreServiceFakerImpl()


    @Singleton
    @Provides
    fun providePlaceService(placesService: PlacesService): PlacesServiceHelper =
        PlacesServiceImpl(placesService = placesService)

}
