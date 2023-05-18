package com.example.recommendtrack.presentation.artist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentMyArtistBinding
import com.example.recommendtrack.presentation.BaseFragment


class MyArtistFragment : BaseFragment<FragmentMyArtistBinding>(R.layout.fragment_my_artist) {

    private lateinit var myArtistRecyclerView:  RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }



    private fun initView() {
        initRecyclerView()
    }


    private fun initRecyclerView() {
        myArtistRecyclerView = binding.myArtistRV
        myArtistRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = MyArtistAdapter()
        }
    }


}

