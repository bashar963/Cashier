package app.snapcart.cashier.data.local

import androidx.room.Dao
import androidx.room.Query
import app.snapcart.cashier.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users where id = :id")
    suspend fun getUser(id: String): User?
}
