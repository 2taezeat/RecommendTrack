package com.example.recommendtrack.presentation.genre

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.FragmentGenreBinding
import com.example.recommendtrack.domain.entity.Genre
import com.example.recommendtrack.presentation.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<FragmentGenreBinding>(R.layout.fragment_genre) {
    private val genreViewModel : GenreViewModel by viewModels()
    private lateinit var genreChipGroup: ChipGroup
    private lateinit var myGenres : MutableList<Genre>
    private lateinit var deleteGenres : MutableList<Genre>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (genreViewModel.genres.value == null) {
            genreViewModel.getAllGenres()
        }

        genreChipGroup = binding.genreChipGroup
        initProperty()
        genreViewModel.myGenres.observe(viewLifecycleOwner) {
            myGenres = it.toMutableList()
        }

        genreViewModel.genres.observe(viewLifecycleOwner) {
            initGenreChipView(it)
        }

//        genreViewModel.refreshToken.observe(viewLifecycleOwner, EventObserver {
//            Snackbar.make(view, R.string.refresh_token_message, Snackbar.LENGTH_SHORT).show()
//        })



        initGenreSaveView()
    }

    private fun initProperty() {
        myGenres = mutableListOf()
        deleteGenres = mutableListOf()
    }

    override fun onStop() {
        super.onStop()
        genreViewModel.getMyGenres()
    }



    private fun initGenreSaveView() {
        binding.genreSaveButton.setOnClickListener {
            val distinctMyGenres = myGenres.distinct()
            val distinctDeleteGenres = deleteGenres.distinct()

            if (distinctMyGenres.size <= 5) {
                genreViewModel.addMyGenres(distinctMyGenres)
                genreViewModel.deleteMyGenres(distinctDeleteGenres)
                Snackbar.make(it, R.string.genre_add_delete_both_success_message, Snackbar.LENGTH_LONG).show()

            } else {
                Snackbar.make(it, R.string.genre_add_only_five_genre_message, Snackbar.LENGTH_LONG).show()
                Snackbar.make(it, R.string.genre_delete_only_success_message, Snackbar.LENGTH_LONG).show()
                genreViewModel.deleteMyGenres(distinctDeleteGenres)
            }
        }

    }


    private fun initGenreChipView(genres: List<Genre>) {
        genres.forEach { genre ->
            val chip = Chip(this.context)
            chip.apply {
                text = genre.name
                setChipCheckedChange(genre)
            }

            genreChipGroup.addView(chip)
        }

    }

    private fun Chip.setChipCheckedChange(genre: Genre) {
        isCheckable = true
        if (myGenres.any { it.name == genre.name }) {
            isChecked = true
        }
        setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                myGenres.add(Genre(name = genre.name))
                deleteGenres.remove(Genre(name = genre.name))
            } else {
                myGenres.remove(Genre(name = genre.name))
                deleteGenres.add(Genre(name = genre.name))
            }
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