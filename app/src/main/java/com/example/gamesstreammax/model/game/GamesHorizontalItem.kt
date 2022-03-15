package com.example.gamesstreammax.model.game

import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.repository.model.CategoryType

data class GamesHorizontalItem(
    val title:String,
    val category:CategoryType,
    val games:List<ListItem>,
): ListItem{
    override val itemId: Long = title.hashCode().toLong()
}