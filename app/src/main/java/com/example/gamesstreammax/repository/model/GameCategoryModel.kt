package com.example.gamesstreammax.repository.model

import com.example.gamesstreammax.core_network.api.PagingState
import com.example.gamesstreammax.core_network.model.GameDto

data class GameCategoryModel(
    val title:String,
    val category:CategoryType,
    val dataState:PagingState<List<GameDto>>)
