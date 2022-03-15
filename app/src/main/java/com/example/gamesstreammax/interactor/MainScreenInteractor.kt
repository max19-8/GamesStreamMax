package com.example.gamesstreammax.interactor

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.repository.model.CategoryType
import kotlinx.coroutines.flow.Flow

interface MainScreenInteractor {
    fun data():Flow<List<ListItem>>
   suspend fun initCategory(categoryType: CategoryType)
   suspend fun tryToLoadMore(categoryType: CategoryType,index:Int)
}