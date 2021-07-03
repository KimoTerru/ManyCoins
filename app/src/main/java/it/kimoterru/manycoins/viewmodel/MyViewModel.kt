package it.kimoterru.manycoins.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.kimoterru.manycoins.network.ApiService
import it.kimoterru.manycoins.network.NetworkHelper
import it.kimoterru.manycoins.network.Resource
import it.kimoterru.manycoins.network.models.CoinResponse
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var coinsLiveData = MutableLiveData<Resource<CoinResponse>>()

    fun getCoinsMain() {
        coinsLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = NetworkHelper.getService().getCoinsWithGlobalAvgPrices(0, 200, "USD")
                coinsLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                coinsLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}