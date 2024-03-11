package me.vaimon.spacex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.spacex.R
import me.vaimon.spacex.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupToolbar()
        setContentView(binding.root)
    }

    private fun setupToolbar() {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).findNavController()
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(navController.graph)
        )
    }
}