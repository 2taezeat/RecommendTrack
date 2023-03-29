package com.example.recommendtrack

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recommendtrack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBottomNavView()
    }

    private fun getNavController(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }


    private fun initBottomNavView() {
        binding.mainBottomNavView.setupWithNavController(getNavController())
    }



}