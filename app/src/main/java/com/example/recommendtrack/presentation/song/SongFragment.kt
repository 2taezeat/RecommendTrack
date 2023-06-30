package com.example.recommendtrack.presentation.song

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentSongBinding
import com.example.recommendtrack.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SongFragment : BaseFragment<FragmentSongBinding>(R.layout.fragment_song) {
    private val viewModel by viewModels<SongViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${viewModel}")
    }


    companion object {
        fun newInstance() =
            SongFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}



