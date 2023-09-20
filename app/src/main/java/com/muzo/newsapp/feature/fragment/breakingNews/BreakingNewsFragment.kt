package com.muzo.newsapp.feature.fragment.breakingNews

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.newsapp.R
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
        newsAdapter = BreakingNewsAdapter(list) {article->
            navigateToDetailFragment(article)
        }
        binding.rvNews.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter // RecyclerView'a adapter'ı bağla

        }
    }

    private fun reSetupAdapter() {
        val categories = listOf(
            "business", "entertainment", "general", "health", "science", "sports", "technology"
        )
        categoryAdapter = CategoryAdapter(categories) { item ->

            sendMessage(requireContext(), " You chose $item news")
            setNewsForCategory(item)
        }
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.adapter = categoryAdapter

    }


    private fun observeData() {


        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.loading -> {
                        binding.rvCategory.visibility = View.GONE
                        binding.rvNews.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    uiState.newsList != null -> {
                        binding.rvCategory.visibility = View.VISIBLE
                        binding.rvNews.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        list = uiState.newsList.articles
                        setupAdapter()
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun setNewsForCategory(category: String) {

        lifecycleScope.launch {
            viewModel.getCategoryNews(category)
            viewModel.uiState.collect { uiState ->

                when {
                    uiState.loading -> {
                        binding.rvCategory.visibility = View.GONE
                        binding.rvNews.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE

                    }

                    uiState.categoryList != null -> {

                        binding.rvCategory.visibility = View.VISIBLE
                        binding.rvNews.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE

                        list = uiState.categoryList.articles
                        setupAdapter()
                    }

                    else -> {

                    }
                }

            }
        }
    }

    private fun sendMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailFragment(item: Article) {

        val bundle = Bundle().apply {
            putString("title", item.title)
            putString("content", item.content)
            putString("urlToImage", item.urlToImage)
            putString("description", item.description)
        }

        findNavController().navigate(R.id.action_breakingNewsFragment_to_detailFragment, bundle)

    }

}