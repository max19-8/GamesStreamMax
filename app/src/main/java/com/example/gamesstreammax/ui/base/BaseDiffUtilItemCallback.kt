package com.example.gamesstreammax.ui.base

import androidx.recyclerview.widget.DiffUtil
import com.example.gamesstreammax.model.base.ListItem

open class BaseDiffUtilItemCallback:DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.equals(newItem)
}