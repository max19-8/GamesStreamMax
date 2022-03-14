package com.example.gamesstreammax.core_network.api

import com.example.gamesstreammax.core_network.model.base.PagedResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RawgApi {
    @GET("/api/games?key=9da2d8e68aba4db999fef88b3020fcc8")
    suspend fun games(@QueryMap params:Map<String,String>): PagedResponse

}