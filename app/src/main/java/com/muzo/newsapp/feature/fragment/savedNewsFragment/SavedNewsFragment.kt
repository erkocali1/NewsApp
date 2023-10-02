package com.muzo.newsapp.feature.fragment.savedNewsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.newsapp.R
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.FragmentSavedNewsBinding
import com.muzo.newsapp.feature.adapters.BreakingNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding
    private val viewModel: SavedNewsViewModel by viewModels()
    private lateinit var newsAdapter: BreakingNewsAdapter
    private lateinit var list: List<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSavedNewsBinding.inflate(layoutInflater, container, false)

        observeData()

        return binding.root
    }


    fun observeData() {

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->

                when {
                    uiState.loading -> {
                        binding.rvNews.visibility = View.GONE
                        binding.emptyPng.visibility = View.GONE
                        binding.emptyTitle.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }


                    uiState.newsList != null -> {
                        binding.rvNews.visibility = View.VISIBLE

                        binding.progressBar.visibility = View.GONE

                        list = uiState.newsList

                        val size = list.size

                        if (size == 0) {
                            binding.emptyPng.visibility = View.VISIBLE
                            binding.emptyTitle.visibility = View.VISIBLE
                        }
                        setupAdapter()
                    }

                    else->{
                        binding.rvNews.visibility = View.VISIBLE
                        binding.emptyPng.visibility = View.VISIBLE
                        binding.emptyTitle.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        list = emptyList() // Boş bir liste oluştur
                        setupAdapter()
                    }

                }

            }
        }
    }

    private fun setupAdapter() {
        newsAdapter = BreakingNewsAdapter(list) { item ->
            navigateToDetailFragment(item)

        }

        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.adapter = newsAdapter


    }

    private fun navigateToDetailFragment(item: Article) {

        val bundle = Bundle().apply {
            putString("title", item.title)
            putString("content", item.content)
            putString("urlToImage", item.urlToImage)
            putString("description", item.description)
            putString("url", item.url)
        }

        findNavController().navigate(R.id.action_savedNewsFragment_to_detailFragment, bundle)

    }

}