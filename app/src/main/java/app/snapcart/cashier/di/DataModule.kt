package app.snapcart.cashier.di

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
    fun provideAuthRepository(authService: AuthService): AuthRepository =
        AuthRepository(authService)

    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository =
        UserRepository(userService)

    @Singleton
    @Provides
    fun provideStoreRepository(storeService: StoreService): StoreRepository =
        StoreRepository(storeService)
}
