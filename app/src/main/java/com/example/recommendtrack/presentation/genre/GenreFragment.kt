package com.example.recommendtrack.presentation.ui.artist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentGenreBinding
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.presentation.ui.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class GenreFragment : BaseFragment<FragmentGenreBinding>(R.layout.fragment_genre) {
    private val viewModel : GenreViewModel by viewModels()
    private lateinit var genreChipGroup: ChipGroup
    private lateinit var myGenres : MutableList<Genre>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genreChipGroup = binding.genreChipGroup
        myGenres = mutableListOf()
        viewModel.myGenres.observe(viewLifecycleOwner, Observer {
            myGenres = it.toMutableList()
        })

        viewModel.genres.observe(viewLifecycleOwner, Observer {
            initGenreChipView(it)
        })

        initGenreSaveView()

    }

    override fun onStop() {
        super.onStop()
        viewModel.getMyGenres()
    }



    private fun initGenreSaveView() {
        binding.genreSaveButton.setOnClickListener {
            val distinctMyGenres = myGenres.distinct()
            Timber.d( "${distinctMyGenres}")

            if (distinctMyGenres.size <= 5) {
                viewModel.saveMyGenres(distinctMyGenres)
            } else {
                Toast.makeText(this.context, R.string.genre_save_fail_toast_message, Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun initGenreChipView(genres: List<Genre>) {
        genres.forEach { genre ->
            val chip = Chip(this.context)
            chip.apply {
                text = genre.name
                isCheckable = true
                if (myGenres.any { it.name == genre.name }) {
                    Timber.d( "${genre}")
                    isChecked = true
                }
                setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked) {
                        myGenres.add(Genre(name = genre.name, true))
                    } else {
                        Timber.d( "${genre.name}")
                        myGenres.remove(Genre(name = genre.name, true))
                    }
                }

            }

            genreChipGroup.addView(chip)
        }

    }


    companion object {
        fun newInstance() =
            GenreFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}