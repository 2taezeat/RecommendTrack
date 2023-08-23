package com.example.recommendtrack.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.work.WorkInfo
import com.example.recommendtrack.R
import com.example.recommendtrack.databinding.ActivityMainBinding
import com.example.recommendtrack.domain.entity.Artist
import com.example.recommendtrack.presentation.artist.ArtistViewModel
import com.example.recommendtrack.presentation.artist.my.MyArtistUpdateCallBack
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MyArtistUpdateCallBack {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()
    private val artistViewModel: ArtistViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNavView()
        artistViewModel.getMyArtists()

        tokenWorkStatus()
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }


    private fun initBottomNavView() {
        binding.mainBottomNavView.setupWithNavController(getNavController())
    }


    override fun deleteMyArtist(deleteArtist: Artist) {
        artistViewModel.deleteMyArtist(deleteArtist)
    }

    override fun addMyArtist(myArtist: Artist) {
        artistViewModel.addMyOneArtist(myArtist)
    }


    override fun getMyArtist() {
        artistViewModel.getMyArtists()
    }


    private fun tokenWorkStatus() {
        mainViewModel.getTokenWorkStatus().observe(this) { workInfo ->
            Timber.d("${workInfo}")
            if (workInfo[0].state == WorkInfo.State.RUNNING) {
                Toast.makeText(this, R.string.refresh_token_message_by_worker_manager,Toast.LENGTH_SHORT).show()
            }
        }

    }


}