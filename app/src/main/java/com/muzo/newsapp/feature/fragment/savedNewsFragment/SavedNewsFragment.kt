package com.muzo.newsapp.feature.fragment.savedNewsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muzo.newsapp.R
import com.muzo.newsapp.databinding.FragmentSavedNewsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedNewsFragment : Fragment() {
    private lateinit var binding: FragmentSavedNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSavedNewsBinding.inflate(layoutInflater, container, false)



        return binding.root
    }


}