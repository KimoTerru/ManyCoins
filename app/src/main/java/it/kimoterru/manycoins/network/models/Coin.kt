package it.kimoterru.manycoins.network.models

import com.google.gson.annotations.SerializedName

data class Coin (
    @SerializedName("id")
    val id: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("rank")
    val rank: Int,

    @SerializedName("price")
    val price: Double,

    @SerializedName("priceChange1h")
    val priceChange1H: Double,
)
