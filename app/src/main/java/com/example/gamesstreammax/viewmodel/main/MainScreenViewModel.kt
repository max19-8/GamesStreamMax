package com.example.gamesstreammax.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesstreammax.interactor.MainScreenInteractor
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.GamesHorizontalItem
import com.example.gamesstreammax.repository.model.CategoryType
import com.example.gamesstreammax.util.ResourceProvider
import com.example.gamesstreammax.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val resource: ResourceProvider,
    private val interactor: MainScreenInteractor
) : BaseViewModel() {

    private var _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>>
        get() = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.data().collect { _data.postValue(it) }
        }
    }

    fun initCategory(item: GamesHorizontalItem) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.initCategory(item.category)
        }
    }

    fun readyToLoadMore(category: CategoryType, index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.tryToLoadMore(category, index)
        }
    }
}