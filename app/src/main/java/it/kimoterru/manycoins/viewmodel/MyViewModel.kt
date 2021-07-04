package it.kimoterru.manycoins.viewmodel

import androidx.lifecycle.*
import it.kimoterru.manycoins.network.NetworkHelper
import it.kimoterru.manycoins.network.Resource
import it.kimoterru.manycoins.models.CoinResponse
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var coinsLiveData = MutableLiveData<Resource<CoinResponse>>()

    fun getCoinsMainOnline() {
        coinsLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val result = NetworkHelper.getService().getCoinsWithGlobalAvgPrices(0, 60, "USD")
                coinsLiveData.postValue(Resource.success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                coinsLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}