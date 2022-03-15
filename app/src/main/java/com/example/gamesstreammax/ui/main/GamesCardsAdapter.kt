package com.example.gamesstreammax.ui.main

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.repository.model.CategoryType
import com.example.gamesstreammax.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class GamesCardsAdapter( onReadyToLoadMore:(Int) -> Unit): AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.wideGameDelegate(onReadyToLoadMore))
            .addDelegate(MainScreenDelegates.thinGameDelegate(onReadyToLoadMore))
            .addDelegate(MainScreenDelegates.wideProgressDelegate())
            .addDelegate(MainScreenDelegates.thinProgressDelegate())
    }
}