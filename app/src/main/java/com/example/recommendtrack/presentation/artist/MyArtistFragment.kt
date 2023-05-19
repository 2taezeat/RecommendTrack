package com.example.recommendtrack.presentation.artist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentMyArtistBinding
import com.example.recommendtrack.presentation.BaseFragment
import timber.log.Timber


class MyArtistFragment : BaseFragment<FragmentMyArtistBinding>(R.layout.fragment_my_artist) {

    private lateinit var myArtistRecyclerView: RecyclerView
    private lateinit var myArtistAdapter: MyArtistAdapter
    private val viewModel: ArtistViewModel by activityViewModels()

    private var myArtistUpdateCallBack: MyArtistUpdateCallBack? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myArtistUpdateCallBack = context as MyArtistUpdateCallBack?

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${ viewModel }")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDetach() {
        super.onDetach()
        myArtistUpdateCallBack = null

    }



    private fun initView() {
        initRecyclerView()
    }


    private fun initRecyclerView() {
        myArtistAdapter = MyArtistAdapter(myArtistUpdateCallBack)
        myArtistAdapter.submitList(viewModel.myArtists.value)
        myArtistRecyclerView = binding.myArtistRV
        myArtistRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = myArtistAdapter
        }

    }



}

