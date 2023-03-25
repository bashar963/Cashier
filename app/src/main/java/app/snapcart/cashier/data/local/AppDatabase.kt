package app.snapcart.cashier.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import app.snapcart.cashier.data.models.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
