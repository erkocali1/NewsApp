package com.muzo.newsapp.feature.fragment.searchNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzo.newsapp.R
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.FragmentSearchBinding
import com.muzo.newsapp.feature.adapters.BreakingNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private val viewModel :SearchNewsViewModel by viewModels()
    private lateinit var newsAdapter: BreakingNewsAdapter
    private lateinit var list: List<Article>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentSearchBinding.inflate(layoutInflater,container,false)
        observeData()
        return binding.root
    }


    private fun observeData(){
        var job: Job? = null
        binding.etName.addTextChangedListener{editable ->
            val string=editable.toString()
            job?.cancel()

            job= MainScope().launch {
                delay(5000L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){

                        lifecycleScope.launch {
                            viewModel.getSearchNews(string,1)
                            viewModel.uiState.collect{uiState->
                               when{
                                   uiState.loading->{
                                       binding.progressBar.visibility = View.GONE
                                       binding.etName.visibility=View.GONE
                                       binding.rvNews.visibility=View.GONE
                                   }
                                   uiState.newsList !=null->{
                                       binding.progressBar.visibility = View.GONE
                                       binding.etName.visibility=View.VISIBLE
                                       binding.rvNews.visibility=View.VISIBLE

                                       list=uiState.newsList.articles
                                       setupAdapter()
                                   }
                               }

                            }
                        }
                    }
                }
            }

        }
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

    private fun navigateToDetailFragment(item: Article) {

        val bundle = Bundle().apply {
            putString("title", item.title)
            putString("content", item.content)
            putString("urlToImage", item.urlToImage)
            putString("description", item.description)
            putString("url", item.url)
        }

        findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)

    }


}