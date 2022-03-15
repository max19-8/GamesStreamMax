package com.example.gamesstreammax.interactor

import com.example.gamesstreammax.core_network.api.GamesRemoteDataSource
import com.example.gamesstreammax.core_network.api.PagingState
import com.example.gamesstreammax.core_network.api.RawgApi
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.*
import com.example.gamesstreammax.repository.GameCategoryRepository
import com.example.gamesstreammax.repository.LatestReleasesGamesRepositoryImpl
import com.example.gamesstreammax.repository.MostAnticipatedGamesRepositoryImpl
import com.example.gamesstreammax.repository.MostRatedGamesRepositoryImpl
import com.example.gamesstreammax.repository.model.CategoryType
import com.example.gamesstreammax.repository.model.GameCategoryModel
import com.example.gamesstreammax.repository.model.Genre
import com.example.gamesstreammax.util.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.IllegalArgumentException

class MainScreenInteractorImpl @Inject constructor(
      api: RawgApi,
      resources:ResourceProvider
) : MainScreenInteractor {

    private val mostAnticipatedGamesRepository: GameCategoryRepository = MostAnticipatedGamesRepositoryImpl(GamesRemoteDataSource(api),resources)
    private val latestReleasesGamesRepository: GameCategoryRepository = LatestReleasesGamesRepositoryImpl(GamesRemoteDataSource(api),resources)
    private val mostRatedGamesRepository: GameCategoryRepository = MostRatedGamesRepositoryImpl(GamesRemoteDataSource(api),resources)


    override fun data(): Flow<List<ListItem>> = combine(
        mostAnticipatedGamesRepository.data(),
        latestReleasesGamesRepository.data(),
        mostRatedGamesRepository.data()
    ) { anticipated, latest, rated ->
        listOf(
            mapToCategory(anticipated,true),
            mapToCategory(latest),
            mapToCategory(rated,true)
        )
    }

    override suspend fun initCategory(categoryType: CategoryType) {
        when(categoryType){
            is CategoryType.MostAnticipated-> mostAnticipatedGamesRepository.init()
            is CategoryType.LatestReleases-> latestReleasesGamesRepository.init()
            is CategoryType.MostRated-> mostRatedGamesRepository.init()
            is Genre -> TODO()
        }
    }

    override suspend fun tryToLoadMore(categoryType: CategoryType,index:Int) {
        when(categoryType){
            is CategoryType.MostAnticipated-> mostAnticipatedGamesRepository.loadMore(index)
            is CategoryType.LatestReleases-> latestReleasesGamesRepository.loadMore(index)
            is CategoryType.MostRated-> mostRatedGamesRepository.loadMore(index)
            is Genre -> TODO()
        }
    }

    private fun mapToCategory(model: GameCategoryModel,wide:Boolean = false): ListItem = when (model.dataState) {
            is PagingState.Initial -> {
                GamesHorizontalItem(
                    title = model.title,
                    category = model.category,
                    games = IntRange(1, 3).map { if (wide) ProgressWideItem  else ProgressThinItem}
                )
            }
            is PagingState.Content -> {
                GamesHorizontalItem(
                    title = model.title,
                    category = model.category,
               games = model.dataState.data.map { if (wide)
                    GameWideItem(
                        id = it.id,
                        title = it.title,
                        image = it.image
                    )
                   else
                   GameThinItem(
                       id = it.id,
                       title = it.title,
                       image = it.image
                   )
                }
                )
            }

        is PagingState.Paging -> {
            GamesHorizontalItem(
                title = model.title,
                category = model.category,
                games = model.dataState.availableContent.map { if (wide)
                    GameWideItem(
                        id = it.id,
                        title = it.title,
                        image = it.image
                    )
                else
                    GameThinItem(
                        id = it.id,
                        title = it.title,
                        image = it.image
                    )
                }
                    .plus(if (wide) ProgressWideItem else ProgressThinItem)
            )
        }
            else -> throw IllegalArgumentException("Wrong paging state ${model.dataState}")
        }
}