package com.muzo.newsapp.feature.fragment.breakingNews

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.FragmentBreakingNewsBinding
import com.muzo.newsapp.feature.adapters.BreakingNewsAdapter
import com.muzo.newsapp.feature.adapters.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private val viewModel: BreakingNewsViewModel by viewModels()
    private lateinit var newsAdapter: BreakingNewsAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var list: List<Article>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentBreakingNewsBinding.inflate(LayoutInflater.from(context), container, false)

        reSetupAdapter()
        observeData()

        return binding.root
    }

    private fun setupAdapter() {
        newsAdapter = BreakingNewsAdapter(list)
        binding.rvNews.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter // RecyclerView'a adapter'ı bağla

        }
    }
    private fun reSetupAdapter(){
        val categories = listOf(
            "Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"
        )
        categoryAdapter = CategoryAdapter(categories) {
            Toast.makeText(requireContext(),"hello",Toast.LENGTH_SHORT).show()
            Log.i("ALOOOOO","problemoooo")
        }
           binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
           binding.rvCategory.adapter = categoryAdapter

    }



    private fun observeData() {


        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.loading -> {
                    }

                    uiState.newsList != null -> {
                        list = uiState.newsList.articles
                        setupAdapter()
                    }

                    else -> {

                    }
                }
            }
        }
    }

   private fun setNewsForCategory() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.loading -> {
                    }

                    uiState.newsList != null -> {
                        list = uiState.categoryList!!.articles
                        setupAdapter()
                    }
                    else -> {

                    }
                }

            }
        }
    }


}