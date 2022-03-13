package com.example.gamesstreammax.ui.main

import com.example.gamesstreammax.databinding.ItemGameThinBinding
import com.example.gamesstreammax.databinding.ItemGameWideBinding
import com.example.gamesstreammax.databinding.ItemGamesHorizontalBinding
import com.example.gamesstreammax.ui.base.ListItem
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenDelegates {

    val gamesHorizontalDelegate =
        adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container ->
                ItemGamesHorizontalBinding.inflate(inflater, container, false).apply {
                    recyclerView.adapter = ListDelegationAdapter(wideGameDelegate, thinGameDelegate)
                }
            }

        ) {
            bind {
                binding.titleTextView.text = item.title
                (binding.recyclerView.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                    items = item.games
                    notifyDataSetChanged()
                }
            }
        }

    private val thinGameDelegate =
        adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
            { inflater, container ->
                ItemGameThinBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.title = item.title
                binding.executePendingBindings()
            }
        }

    private val wideGameDelegate =
        adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
            { inflater, container ->
                ItemGameWideBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.imageView.setBackgroundColor(item.hashCode())
                binding.title = item.title
                binding.executePendingBindings()
            }
        }
}