package com.muzo.newsapp.feature.fragment.detailFragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.muzo.newsapp.R
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var list: List<Article>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        loadData()
        heartClicked()
        return binding.root
    }


    private fun getData() {
        val title = arguments?.getString("title")
        val url = arguments?.getString("url")
        val urlToImage = arguments?.getString("urlToImage")


        val webView = binding.webView
        val progressBar = binding.progressBar

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.INVISIBLE
            }
        }

        webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url!!)
    }

    private fun loadData() {

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.loading -> {

                        binding.webView.visibility=View.GONE
                        binding.progressBar.visibility=View.VISIBLE

                    }

                    uiState.newsLocalData != null -> {
                        list=uiState.newsLocalData
                        isFavCheck()
                        getData()
                        binding.webView.visibility=View.VISIBLE
                        binding.progressBar.visibility=View.GONE
                    }

                }


            }
        }
    }

    private fun saveNewsRoom() {

        val listSave = arrayListOf<Article>()
        val title = arguments?.getString("title")
        val url = arguments?.getString("url")
        val urlToImage = arguments?.getString("urlToImage")
        val content = arguments?.getString("content")

        val data = Article(
            author = "",
            content = content!!,
            description = "",
            publishedAt = "",
            title = title!!,
            url = url!!,
            urlToImage = urlToImage!!,
            id = 0
        )
        listSave.add(data)
        lifecycleScope.launch {
            viewModel.saveNews(listSave as List<Article>)
        }
    }

    private fun toastMessage(message: String) {

        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun heartClicked() {
        val emptyHeartDrawableName = "ic_empty_heart"
        val filledHeartDrawableName = "ic_save"

        binding.fabButton.tag = emptyHeartDrawableName

        binding.fabButton.setOnClickListener {
            val currentDrawableName = binding.fabButton.tag as? String

            if (currentDrawableName == emptyHeartDrawableName) {
                binding.fabButton.setImageResource(
                    resources.getIdentifier(
                        filledHeartDrawableName, "drawable", requireContext().packageName
                    )
                )
                binding.fabButton.tag = filledHeartDrawableName

                toastMessage("This news added saved")
                saveNewsRoom()


            } else {
                // Boş kalp görselini yükleyin
                binding.fabButton.setImageResource(
                    resources.getIdentifier(
                        emptyHeartDrawableName, "drawable", requireContext().packageName
                    )
                )
                binding.fabButton.tag = emptyHeartDrawableName
                distractSave()
                toastMessage("This news distract saved")
            }
        }

    }

    private fun distractSave() {

        val title = arguments?.getString("title")


        lifecycleScope.launch {
            viewModel.deleteNews(title!!)
        }
    }

    private fun isFavCheck() {
        val title = arguments?.getString("title")

        // Check if the trackName exists in the list of Article
        val isFav = list!!.any { it.title == title }

        // Update the heart icon based on the isFav status
        if (isFav) {
            binding.fabButton.setImageResource(R.drawable.ic_save)
            binding.fabButton.tag = "ic_save"
        } else {
            binding.fabButton.setImageResource(R.drawable.ic_empty_heart)
            binding.fabButton.tag = "ic_empty_heart"
        }
    }


}


