package com.example.recommendtrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recommendtrack.databinding.FragmentRecommendTrackBinding


class RecommendTrackFragment : BaseFragment<FragmentRecommendTrackBinding>(R.layout.fragment_recommend_track, RecommendTrackViewModel().javaClass) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("viewModel_RecommendTrackFragment", "${viewModel}")
    }



    companion object {

        fun newInstance() =
            RecommendTrackFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}