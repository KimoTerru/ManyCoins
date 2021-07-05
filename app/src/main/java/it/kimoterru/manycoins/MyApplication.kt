package it.kimoterru.manycoins

import android.app.Application
import it.kimoterru.manycoins.database.MyDataBase

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    val db by lazy {
        MyDataBase.DataBaseHelper.getDataBase(this)
    }

    init {
        instance = this
    }
}