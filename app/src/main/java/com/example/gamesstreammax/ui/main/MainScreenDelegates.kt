package com.example.gamesstreammax.ui.main

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.gamesstreammax.R
import com.example.gamesstreammax.databinding.*
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.model.game.*
import com.example.gamesstreammax.repository.model.CategoryType
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import timber.log.Timber

object MainScreenDelegates {

    fun gamesHorizontalDelegate(onItemBind:(GamesHorizontalItem) -> Unit,
                                onReadyToLoadMore:(CategoryType,Int) -> Unit,) =
        adapterDelegateViewBinding<GamesHorizontalItem, ListItem, ItemGamesHorizontalBinding>(
            { inflater, container ->
                ItemGamesHorizontalBinding.inflate(inflater, container, false)
            }
        ) {
            val adapter = GamesCardsAdapter{pos -> (onReadyToLoadMore).invoke(item.category,pos)}
            binding.recyclerView.adapter = adapter

            bind {
                onItemBind.invoke(item)
                binding.titleTextView.text = item.title
                adapter.items = item.games
            }
        }

    fun thinProgressDelegate() =
        adapterDelegateViewBinding<ProgressThinItem, ListItem, ItemProgressThinBinding>(
            { inflater, container ->
                ItemProgressThinBinding.inflate(inflater, container, false)
            }
        ) {}

    fun thinGameDelegate( onReadyToLoadMore:(Int) -> Unit) =
        adapterDelegateViewBinding<GameThinItem, ListItem, ItemGameThinBinding>(
            { inflater, container ->
                ItemGameThinBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(
                        resources.getDimensionPixelOffset(R.dimen.game_card_thin_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_thin_height)
                    )
                    .transform(CenterCrop(), RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius)))
                    .transition(withCrossFade())
                    .into(binding.imageView)
                binding.title = item.title
                binding.executePendingBindings()
                Timber.e("Bind $bindingAdapterPosition")
                onReadyToLoadMore.invoke(bindingAdapterPosition)
            }
            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                    Glide.with(binding.root).clear(binding.imageView)
            }
        }

    fun wideProgressDelegate() =
        adapterDelegateViewBinding<ProgressWideItem, ListItem, ItemProgressWideBinding>(
            { inflater, container ->
                ItemProgressWideBinding.inflate(inflater, container, false)
            }
        ) {}

    fun wideGameDelegate( onReadyToLoadMore:(Int) -> Unit) =
        adapterDelegateViewBinding<GameWideItem, ListItem, ItemGameWideBinding>(
            { inflater, container ->
                ItemGameWideBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.image)
                    .override(
                        resources.getDimensionPixelOffset(R.dimen.game_card_wide_width),
                        resources.getDimensionPixelOffset(R.dimen.game_card_wide_height)
                    )
                    .transform(CenterCrop(), RoundedCorners(resources.getDimensionPixelOffset(R.dimen.game_card_radius)))
                    .transition(withCrossFade())
                    .into(binding.imageView)
                binding.title = item.title
                binding.executePendingBindings()
                onReadyToLoadMore.invoke(bindingAdapterPosition)
            }
            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true)
                    Glide.with(binding.root).clear(binding.imageView)
            }
        }
}