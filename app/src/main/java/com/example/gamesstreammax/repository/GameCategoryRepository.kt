package com.example.gamesstreammax.repository

import com.example.gamesstreammax.repository.model.GameCategoryModel
import kotlinx.coroutines.flow.Flow

interface GameCategoryRepository {

    fun data(): Flow<GameCategoryModel>

   suspend fun init()
   suspend fun loadMore(index:Int)

}