package com.example.gamesstreammax.repository

import com.example.gamesstreammax.R
import com.example.gamesstreammax.core_network.api.GamesRemoteDataSource
import com.example.gamesstreammax.core_network.api.params.GamesApiParams
import com.example.gamesstreammax.repository.model.CategoryType
import com.example.gamesstreammax.repository.model.GameCategoryModel
import com.example.gamesstreammax.util.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostAnticipatedGamesRepositoryImpl @Inject constructor(
    private val dataSource:GamesRemoteDataSource,
    private val resources:ResourceProvider
):GameCategoryRepository {

    override fun data(): Flow<GameCategoryModel>  =
        dataSource.observe().map { GameCategoryModel(
            title = resources.string(R.string.most_anticipated_text),
            category = CategoryType.MostAnticipated,
            dataState = it) }

    override suspend fun init() {
      dataSource.initialLoading(GamesApiParams(dates = "2022-03-14,2023-03-14", ordering = "-added"))
    }
    override suspend fun loadMore(index:Int) {
        dataSource.loadMore(index)
    }
}