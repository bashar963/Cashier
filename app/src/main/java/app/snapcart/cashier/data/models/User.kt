package app.snapcart.cashier.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// mock class
@Entity(tableName = "users")
data class User(
    @PrimaryKey val id:Int,
    val name:String
    )
