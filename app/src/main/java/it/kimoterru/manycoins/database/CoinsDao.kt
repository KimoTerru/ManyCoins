package it.kimoterru.manycoins.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.kimoterru.manycoins.models.Coin

@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins_table")
    suspend fun getAllCoins(): List<Coin>

    @Query("DELETE FROM coins_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<Coin>)
}