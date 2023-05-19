package com.example.recommendtrack.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.ActivityMainBinding
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.presentation.artist.ArtistViewModel
import com.example.recommendtrack.presentation.artist.MyArtistUpdateCallBack
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MyArtistUpdateCallBack {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel : MainViewModel by viewModels()
    private val artistViewModel : ArtistViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNavView()
        Timber.d("${mainViewModel}")
        Timber.d("${artistViewModel}")
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }


    private fun initBottomNavView() {
        binding.mainBottomNavView.setupWithNavController(getNavController())
    }


    override fun deleteMyArtist(deleteArtist: Artist) {
        artistViewModel.deleteMyArtist(deleteArtist)
    }

    override fun addMyArtist(myArtist: Artist) {
        artistViewModel.addMyArtist(myArtist)
    }


    override fun getMyArtist() {
        artistViewModel.getMyArtists()
    }

}