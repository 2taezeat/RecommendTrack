package com.example.recommendtrack.presentation.artist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentMyArtistBinding
import com.example.recommendtrack.presentation.BaseFragment
import timber.log.Timber


class MyArtistFragment : BaseFragment<FragmentMyArtistBinding>(R.layout.fragment_my_artist) {

    private lateinit var myArtistRecyclerView: RecyclerView
    private lateinit var myArtistAdapter: MyArtistAdapter
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private var myArtistUpdateCallBack: MyArtistUpdateCallBack? = null
    private lateinit var itemTouchHelper : ItemTouchHelper


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myArtistUpdateCallBack = context as MyArtistUpdateCallBack?

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${ artistViewModel }")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        artistViewModel.myArtists.observe(viewLifecycleOwner, Observer { it ->
            myArtistAdapter.submitList(it)
        })

    }

    override fun onStop() {
        super.onStop()
        Timber.d("${ myArtistAdapter.currentList }")
        artistViewModel.deleteAllMyArtists()


    }


    override fun onDetach() {
        super.onDetach()
        myArtistUpdateCallBack = null

    }



    private fun initView() {
        initRecyclerView()
    }


    private fun initRecyclerView() {
        Timber.d("initRecyclerView")

        myArtistAdapter = MyArtistAdapter(myArtistUpdateCallBack)
        myArtistAdapter.apply {
            submitList(artistViewModel.myArtists.value)
            setOnItemClickListener {
                val action = MyArtistFragmentDirections.actionMyArtistFragmentToArtistInfoFragment(it)
                findNavController().navigate(action)
            }
        }

        myArtistRecyclerView = binding.myArtistRV
        myArtistRecyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = myArtistAdapter
        }

        itemTouchHelper = ItemTouchHelper(ItemTouchCallback( listener = myArtistAdapter))
        itemTouchHelper.attachToRecyclerView(myArtistRecyclerView)
    }



}

