package app.snapcart.cashier.di

import app.snapcart.cashier.data.data_store.AuthRemoteDataSource
import app.snapcart.cashier.data.data_store.StoreRemoteDataSource
import app.snapcart.cashier.data.data_store.UserRemoteDataSource
import app.snapcart.cashier.data.remote.auth.AuthService
import app.snapcart.cashier.data.remote.store.StoreService
import app.snapcart.cashier.data.remote.user.UserService
import app.snapcart.cashier.data.repo.auth.AuthRepository
import app.snapcart.cashier.data.repo.store.StoreRepository
import app.snapcart.cashier.data.repo.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authService: AuthService): AuthRemoteDataSource =
        AuthRemoteDataSource(authService)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService): UserRemoteDataSource =
        UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideStoreRemoteDataSource(storeService: StoreService): StoreRemoteDataSource =
        StoreRemoteDataSource(storeService)

    @Singleton
    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepository(authRemoteDataSource)

    @Singleton
    @Provides
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepository(userRemoteDataSource)

    @Singleton
    @Provides
    fun provideStoreRepository(storeRemoteDataSource: StoreRemoteDataSource): StoreRepository =
        StoreRepository(storeRemoteDataSource)
}
