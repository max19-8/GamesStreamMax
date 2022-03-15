package com.example.gamesstreammax.ui.main

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.GamesHorizontalItem
import com.example.gamesstreammax.repository.model.CategoryType
import com.example.gamesstreammax.ui.base.BaseDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter(onItemBind:(GamesHorizontalItem) -> Unit,
                        onReadyToLoadMore:(CategoryType,Int) -> Unit,
) : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(MainScreenDelegates.gamesHorizontalDelegate( onItemBind = onItemBind,onReadyToLoadMore = onReadyToLoadMore))

    }

}