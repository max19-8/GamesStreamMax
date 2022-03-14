package com.example.gamesstreammax.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesstreammax.core_network.api.RawgApi
import com.example.gamesstreammax.core_network.di.NetworkComponent
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.*
import com.example.gamesstreammax.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel :BaseViewModel(){

    private val api = NetworkComponent.createApi()
    private var _data=MutableLiveData<List<ListItem>>()
    val data:LiveData<List<ListItem>>
    get() = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(getLoaders())
            val items = getItems()
            _data.postValue(items)
        }
    }
    private suspend fun getItems():List<ListItem> {
        val theMostAnticipated = api.games(
            mapOf(
                "dates" to "2022-03-14,2023-03-14",
                "ordering" to "-added"))
        val latestReleasesResponse = api.games(
            mapOf(
                "dates" to "2021-02-01,2022-03-14"))
        val mostRatedResponse = api.games(
            mapOf(
                "dates" to "2022-01-01,2022-03-14",
                "ordering" to "-rated"))
        val theMostAnticipatedItems = theMostAnticipated.results.map { GameWideItem(id = it.id, title = it.title, image = it.image) }
        val latestReleasesItems = latestReleasesResponse.results.map { GameThinItem(id = it.id, title = it.title, image = it.image) }
        val mostRatedItems = mostRatedResponse.results.map { GameThinItem(id = it.id, title = it.title, image = it.image) }
        return listOf(GamesHorizontalItem(
            title = "The most anticipated",
            games =  theMostAnticipatedItems
        ), GamesHorizontalItem(
            title = "Latest releases",
            games = latestReleasesItems
        ), GamesHorizontalItem(
            title = "Most rated in 2022",
            games = mostRatedItems
        ))
    }



    private  fun getLoaders():List<ListItem> =  listOf(GamesHorizontalItem(
        title = "The most anticipated",
        games = IntRange(1,2).map { ProgressWideItem }
    ), GamesHorizontalItem(
        title = "Latest releases",
        games = IntRange(1,3).map { ProgressThinItem }
    ), GamesHorizontalItem(
        title = "Most rated in 2022",
        games = IntRange(1,2).map { ProgressWideItem}
    ))
}