package app.snapcart.cashier.di

import app.snapcart.cashier.BuildConfig
import app.snapcart.cashier.data.data_store.AuthRemoteDataSource
import app.snapcart.cashier.data.remote.auth.AuthService
import app.snapcart.cashier.data.remote.auth.AuthServiceImpl
import app.snapcart.cashier.data.repo.auth.AuthRepository
import app.snapcart.cashier.utils.CashierResponseClientInterceptor
import com.snapcart.protos.api.common.v1.AuthServiceGrpcKt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideManagedChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            BuildConfig.API_URL,
            BuildConfig.API_HOST.toInt()
        ).apply {
            if (BuildConfig.HTTP_LOGGING_ENABLED) {
                intercept(CashierResponseClientInterceptor())
            }
        }
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthGrpcService(channel: ManagedChannel): AuthServiceGrpcKt.AuthServiceCoroutineStub {
        return AuthServiceGrpcKt.AuthServiceCoroutineStub(channel = channel)
    }

    @Singleton
    @Provides
    fun provideAuthService(authServiceStub: AuthServiceGrpcKt.AuthServiceCoroutineStub): AuthService =
        AuthServiceImpl(authServiceGrpc = authServiceStub)

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSource(authService)

    @Singleton
    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepository(authRemoteDataSource)
}
