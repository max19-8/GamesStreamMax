package com.example.gamesstreammax.model.game

import com.example.gamesstreammax.model.base.ListItem

data class GamesHorizontalItem(
    val title:String,
    val games:List<ListItem>,
): ListItem{
    override val itemId: Long = title.hashCode().toLong()
}