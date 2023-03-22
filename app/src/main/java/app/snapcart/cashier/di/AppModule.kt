package app.snapcart.cashier.di

import android.content.Context
import app.snapcart.cashier.utils.CashierStringProvider
import com.google.i18n.phonenumbers.PhoneNumberUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePhoneNumberUtil(): PhoneNumberUtil = PhoneNumberUtil.getInstance()

    @Provides
    @Singleton
    fun provideCashierStringProvider(
        @ApplicationContext context: Context
    ): CashierStringProvider = CashierStringProvider(context)
}
