package com.example.gamesstreammax.core_network.api

import com.example.gamesstreammax.core_network.api.params.GamesApiParams
import com.example.gamesstreammax.core_network.model.GameDto
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.text.FieldPosition
import javax.inject.Inject

class GamesRemoteDataSource @Inject constructor(
    private val api: RawgApi
    )
{
    private val channel = ConflatedBroadcastChannel<PagingState<List<GameDto>>>(PagingState.Initial)
    private var params:GamesApiParams? = null
    private var page =1

    @Synchronized
  suspend  fun initialLoading(params: GamesApiParams){
      if (channel.value is PagingState.Initial){
          val response = api.games(params.applyPagingParams())
          this.params = params
          channel.send(PagingState.Content(response.results))
      }
    }
    @Synchronized
    suspend fun loadMore(candidatePosition: Int){
        val params = this.params ?: return
        val cache = channel.value
        if (cache is PagingState.Content && candidatePosition == cache.data.size - 1){
            channel.send(PagingState.Paging(cache.data))
         val response = api.games(params = params.applyPagingParams(page =  page + 1) )
            channel.send(PagingState.Content( cache.data.plus(response.results)))
            page += 1
        }
    }

    fun observe():Flow<PagingState<List<GameDto>>> = channel.asFlow()

    private fun GamesApiParams.applyPagingParams(page:Int = 1):Map<String,String> = toMap().toMutableMap().apply {
         put("page", page.toString())
         put("page_size", DEFAULT_PAGE_SIZE.toString())
    }

    private companion object{
        const val DEFAULT_PAGE_SIZE = 20

    }
}