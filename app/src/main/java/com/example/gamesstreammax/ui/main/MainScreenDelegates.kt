package com.example.gamesstreammax.ui.main

import android.app.Activity
import com.bumptech.glide.Glide
import com.example.gamesstreammax.R
import com.example.gamesstreammax.databinding.*
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.*
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenDelegates {

    val gamesHorizontalDelegate =
        adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container ->
                ItemGamesHorizontalBinding.inflate(inflater, container, false)
            }
        ) {
            val adapter = GamesCardsAdapter()
           binding.recyclerView.adapter = adapter

            bind {
                binding.titleTextView.text = item.title
                adapter.items = item.games
            }
        }
     val thinProgressDelegate =
        adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressThinBinding>(
            { inflater, container ->
                ItemProgressThinBinding.inflate(inflater, container, false)
            }
        ) {}

     val thinGameDelegate =
        adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
            { inflater, container ->
                ItemGameThinBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(resources.getDimensionPixelOffset(R.dimen.game_card_thin_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_thin_height))
                    .centerCrop()
                    .into(binding.imageView)
                binding.title = item.title
                binding.executePendingBindings()
            }
            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                    Glide.with(binding.root).clear(binding.imageView)
            }
        }
     val wideProgressDelegate =
        adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressWideBinding>(
            { inflater, container ->
                ItemProgressWideBinding.inflate(inflater, container, false) }
        ) {}

     val wideGameDelegate =
        adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
            { inflater, container ->
                ItemGameWideBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(resources.getDimensionPixelOffset(R.dimen.game_card_wide_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_wide_height))
                    .centerCrop()
                    .into(binding.imageView)
                binding.title = item.title
                binding.executePendingBindings()
            }
            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                Glide.with(binding.root).clear(binding.imageView)
            }
        }
}