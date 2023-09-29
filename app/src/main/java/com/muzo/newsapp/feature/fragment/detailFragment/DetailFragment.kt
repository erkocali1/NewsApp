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
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        getData()
        return binding.root
    }


    private fun getData() {
        val title = arguments?.getString("title")
        val url = arguments?.getString("url")
        val urlToImage = arguments?.getString("urlToImage")

        binding.ivNews.load(urlToImage)
        binding.appId.text = title

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

    private fun saveNewsRoom() {

        val listSave = arrayListOf<Article>()
        val title = arguments?.getString("title")
        val url = arguments?.getString("url")
        val urlToImage = arguments?.getString("urlToImage")

        val data = Article(
            author = "",
            content = "",
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

    private fun heartClicked(){

    }

    private fun distractSave() {

        val uid = arguments?.getInt("uid")


        lifecycleScope.launch {
            viewModel.deleteNews(uid!!)
        }
    }


}


