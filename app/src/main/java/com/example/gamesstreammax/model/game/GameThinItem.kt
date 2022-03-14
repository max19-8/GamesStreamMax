package com.example.gamesstreammax.model.game

import com.example.gamesstreammax.model.base.ListItem

data class GameThinItem(
    val id:Long,
    val title:String,
    val image:String
): ListItem{
    override val itemId: Long = id
}