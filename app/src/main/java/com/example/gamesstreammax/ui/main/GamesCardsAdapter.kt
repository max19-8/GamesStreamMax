package com.example.gamesstreammax.ui.main

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class GamesCardsAdapter: AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.wideGameDelegate)
            .addDelegate(MainScreenDelegates.thinGameDelegate)
            .addDelegate(MainScreenDelegates.wideProgressDelegate)
            .addDelegate(MainScreenDelegates.thinProgressDelegate)
    }
}