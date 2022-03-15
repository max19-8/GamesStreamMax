package com.example.gamesstreammax.repository.model

sealed class CategoryType{
    object MostAnticipated:CategoryType()
    object LatestReleases:CategoryType()
    object MostRated:CategoryType()
}
data class Genre(val id:Long):CategoryType()
