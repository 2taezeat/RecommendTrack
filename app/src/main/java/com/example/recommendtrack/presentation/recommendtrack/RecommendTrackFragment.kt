package com.example.recommendtrack.presentation.recommendtrack

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentRecommendTrackBinding
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.example.recommendtrack.presentation.ui.artist.ArtistViewModel
import com.example.recommendtrack.presentation.ui.recommendtrack.RecommendTrackViewModel
import timber.log.Timber


class RecommendTrackFragment : BaseFragment<FragmentRecommendTrackBinding>(R.layout.fragment_recommend_track) {
    private val viewModel by viewModels<RecommendTrackViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${viewModel}")
    }



    companion object {

        fun newInstance() =
            RecommendTrackFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}