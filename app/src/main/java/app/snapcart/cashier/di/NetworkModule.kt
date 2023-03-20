package app.snapcart.cashier.di

import app.snapcart.cashier.BuildConfig
import app.snapcart.cashier.data.models.AuthServiceStub
import app.snapcart.cashier.data.models.StoreServiceStub
import app.snapcart.cashier.data.models.UserServiceStub
import app.snapcart.cashier.data.remote.auth.AuthService
import app.snapcart.cashier.data.remote.auth.AuthServiceFakerImpl
import app.snapcart.cashier.data.remote.auth.AuthServiceImpl
import app.snapcart.cashier.data.remote.store.StoreService
import app.snapcart.cashier.data.remote.store.StoreServiceFakerImpl
import app.snapcart.cashier.data.remote.store.StoreServiceImpl
import app.snapcart.cashier.data.remote.user.UserService
import app.snapcart.cashier.data.remote.user.UserServiceFakerImpl
import app.snapcart.cashier.data.remote.user.UserServiceImpl
import app.snapcart.cashier.utils.CashierResponseClientInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideManagedChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            "10.0.2.2",
            50051
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
    fun provideAuthGrpcService(channel: ManagedChannel): AuthServiceStub {
        return AuthServiceStub(channel = channel)
    }

    @Singleton
    @Provides
    fun provideStoreGrpcService(channel: ManagedChannel): StoreServiceStub {
        return StoreServiceStub(channel)
    }

    @Singleton
    @Provides
    fun provideUserGrpcService(channel: ManagedChannel): UserServiceStub {
        return UserServiceStub(channel = channel)
    }

    @Singleton
    @Provides
    fun provideAuthService(authServiceStub: AuthServiceStub): AuthService =
        if (BuildConfig.USE_FAKER.toBoolean()) {
            AuthServiceFakerImpl()
        } else {
            AuthServiceImpl(authServiceGrpc = authServiceStub)
        }

    @Singleton
    @Provides
    fun provideUserService(userServiceStub: UserServiceStub): UserService =
        if (BuildConfig.USE_FAKER.toBoolean()) {
            UserServiceFakerImpl()
        } else {
            UserServiceImpl(userServiceGrpc = userServiceStub)
        }

    @Singleton
    @Provides
    fun provideStoreService(storeServiceStub: StoreServiceStub): StoreService =
        if (BuildConfig.USE_FAKER.toBoolean()) {
            StoreServiceFakerImpl()
        } else {
            StoreServiceImpl(storeServiceGrpc = storeServiceStub)
        }
}
