package app.snapcart.cashier.di

import app.snapcart.cashier.data.data_store.AuthRemoteDataSource
import app.snapcart.cashier.data.remote.AuthService
import app.snapcart.cashier.data.repo.auth.AuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            // Add more interceptor here
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://google.com")// TODO need base URL
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideAuthService(retrofit : Retrofit): AuthService  = retrofit.create(AuthService::class.java)


    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authService : AuthService): AuthRemoteDataSource = AuthRemoteDataSource(authService)

    @Singleton
    @Provides
    fun provideAuthRepository(authRemoteDataSource : AuthRemoteDataSource): AuthRepository = AuthRepository(authRemoteDataSource)

}