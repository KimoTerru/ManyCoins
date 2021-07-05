package it.kimoterru.manycoins.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "coins_table"
)
data class Coin (
    @PrimaryKey(autoGenerate = true)
    val databaseid: Int,

    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    val icon: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "symbol")
    @SerializedName("symbol")
    val symbol: String,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Double,

    @ColumnInfo(name = "priceChange1h")
    @SerializedName("priceChange1h")
    val priceChange1H: Double,
)
