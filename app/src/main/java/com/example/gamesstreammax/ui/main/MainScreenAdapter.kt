package com.example.gamesstreammax.ui.main

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(MainScreenDelegates.gamesHorizontalDelegate)
    }

}