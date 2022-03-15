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

class MostRatedGamesRepositoryImpl @Inject constructor(
    private val dataSource:GamesRemoteDataSource,
    private val resources:ResourceProvider
):GameCategoryRepository {

    override fun data(): Flow<GameCategoryModel>  =
        dataSource.observe().map { GameCategoryModel(
            title = resources.string(R.string.most_rated_in_2022_text),
            category = CategoryType.MostRated,
            dataState = it) }

    override suspend fun init() {
        dataSource.initialLoading(GamesApiParams(dates = "2022-01-01,2022-03-14", ordering = "-rated"))
    }
    override suspend fun loadMore(index:Int) {
        dataSource.loadMore(index)
    }
}