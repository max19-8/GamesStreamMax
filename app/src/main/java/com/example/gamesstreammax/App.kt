package com.example.gamesstreammax

import android.app.Application
import com.example.gamesstreammax.core_network.di.DaggerNetworkComponent
import com.example.gamesstreammax.di.DaggerAppComponent
import timber.log.Timber

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initDi()
    }
    private fun initDi() {
       DI.appComponent =  DaggerAppComponent.builder()
           .appContext(this)
           .build()

        DI.networkComponent = DaggerNetworkComponent.create()
    }
}