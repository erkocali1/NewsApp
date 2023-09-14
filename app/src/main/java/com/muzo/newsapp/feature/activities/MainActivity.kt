package com.muzo.newsapp.feature.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.muzo.newsapp.R
import com.muzo.newsapp.databinding.ActivityMainBinding
import com.muzo.newsapp.feature.fragment.breakingNews.BreakingNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: BreakingNewsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupBottomBar()
    }

    private fun setupBottomBar(){
        val bottomNavigationBar=binding.bottomNavBar
        val navController=findNavController(R.id.fragmentContainerView  )

        bottomNavigationBar.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.savedNewsFragment -> {
                    // Ana Sayfa seçildiğinde yapılacak işlemler
                    navController.navigate(R.id.savedNewsFragment)
                }
                R.id.searchFragment -> {
                    // Panel seçildiğinde yapılacak işlemler
                    navController.navigate(R.id.searchFragment)
                }
                R.id.breakingNewsFragment -> {
                    // Bildirimler seçildiğinde yapılacak işlemler
                    navController.navigate(R.id.breakingNewsFragment)
                }
            }
        }

    }
}