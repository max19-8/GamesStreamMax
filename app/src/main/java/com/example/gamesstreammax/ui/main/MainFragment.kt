package com.example.gamesstreammax.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.gamesstreammax.R
import com.example.gamesstreammax.databinding.FragmentMainBinding
import com.example.gamesstreammax.model.base.ListItem
import com.example.gamesstreammax.ui.base.viewBinding
import com.example.gamesstreammax.viewmodel.main.MainScreenViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainFragment : Fragment(R.layout.fragment_main) {
    private  val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel>()
    private val adapter = MainScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recyclerView.adapter = adapter
                viewModel.data.observe(viewLifecycleOwner) {
                    adapter.items = it

            }
        }
    }
}