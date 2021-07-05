package it.kimoterru.manycoins.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.kimoterru.manycoins.models.Coin

@Database(
    entities = [Coin::class],
    version = 1
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getCoinsDao(): CoinsDao

    object DataBaseHelper {
        fun getDataBase(context: Context): MyDataBase = Room.databaseBuilder(
            context, MyDataBase::class.java,
            "coin_db.db"
        ).build()
    }
}