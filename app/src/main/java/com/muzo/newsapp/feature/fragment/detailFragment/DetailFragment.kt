package com.muzo.newsapp.feature.fragment.detailFragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import coil.load
import com.muzo.newsapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

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



}


