package com.example.gamesstreammax.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.gamesstreammax.R
import com.example.gamesstreammax.databinding.FragmentMainBinding
import com.example.gamesstreammax.ui.base.ListItem
import com.example.gamesstreammax.ui.base.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainFragment : Fragment(R.layout.fragment_main) {
    private  val binding by viewBinding { FragmentMainBinding.bind(it) }

    private val adapter = ListDelegationAdapter<List<ListItem>>(
    MainScreenDelegates.gamesHorizontalDelegate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recyclerView.adapter = adapter
            adapter.apply {
                items = listOf(GamesHorizontalItem(
                        title = "Wide Games",
                    games = IntRange(1,20).map { GameWideItem(it.toLong(),"Game Title $it") }
                ), GamesHorizontalItem(
                    title = "Thin Games",
                    games = IntRange(1,20).map { GameThinItem(it.toLong(),"Game Title $it") }
                ), GamesHorizontalItem(
                    title = "Wide Games",
                    games = IntRange(1,20).map { GameWideItem(it.toLong(),"Game Title $it") }))
                notifyDataSetChanged()
            }
        }
    }
}