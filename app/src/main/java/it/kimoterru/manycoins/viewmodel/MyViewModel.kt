package it.kimoterru.manycoins.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.kimoterru.manycoins.MyApplication
import it.kimoterru.manycoins.models.Coin
import it.kimoterru.manycoins.network.NetworkHelper
import it.kimoterru.manycoins.util.Resource
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    var coinsLiveData = MutableLiveData<Resource<List<Coin>>>()
    private val coinsDao = MyApplication.instance.db.getCoinsDao()

    fun getCoinsMain() {
        coinsLiveData.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                val dbResult = coinsDao.getAllCoins()
                if (dbResult.isNotEmpty()) {
                    coinsLiveData.postValue(Resource.success(dbResult))
                } else {
                    val networkCoin = NetworkHelper
                        .getService().getCoinsWithGlobalAvgPrices(0, 20, "USD").coins
                    coinsDao.insertCoins(networkCoin)
                    coinsLiveData.postValue(Resource.success(networkCoin))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                coinsLiveData.postValue(Resource.error(e.message ?: "none"))
            }
        }
    }
}