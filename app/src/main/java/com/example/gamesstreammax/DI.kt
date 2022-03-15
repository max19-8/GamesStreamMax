package com.example.gamesstreammax

import com.example.gamesstreammax.core_network.di.NetworkComponent
import com.example.gamesstreammax.di.AppComponent

object DI {
    lateinit var appComponent: AppComponent
    internal set
    lateinit var networkComponent: NetworkComponent
    internal set
}