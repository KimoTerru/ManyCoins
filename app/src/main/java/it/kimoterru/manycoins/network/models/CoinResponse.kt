package it.kimoterru.manycoins.network.models

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("coins")
    val coins: List<Coin>
)
