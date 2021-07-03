package it.kimoterru.manycoins.network

import it.kimoterru.manycoins.network.models.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/public/v1/coins")
    suspend fun getCoinsWithGlobalAvgPrices(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("currency") currency: String
    ): CoinResponse
}