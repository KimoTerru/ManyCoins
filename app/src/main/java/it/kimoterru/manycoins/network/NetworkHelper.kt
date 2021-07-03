package it.kimoterru.manycoins.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinstats.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var service: ApiService? = null

    fun getService() : ApiService {
        if (service == null) {
            service = retrofit.create(ApiService::class.java)
        }
        return service!!
    }
}