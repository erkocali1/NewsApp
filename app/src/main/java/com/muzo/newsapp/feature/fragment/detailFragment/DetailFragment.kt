package com.muzo.newsapp.feature.fragment.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val content = arguments?.getString("content")
        val urlToImage = arguments?.getString("urlToImage")
        val description = arguments?.getString("description")

        binding.ivNews.load(urlToImage)
        binding.newsContent.text=content
        binding.appId.text=title
    }


}


