package com.muzo.newsapp.feature.fragment.infoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muzo.newsapp.R
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private val viewModel:InfoFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        checkLogin()
        clickEvent()
        return binding.root
    }

    private fun navigateFragment(destinationId: Int) {
        findNavController().navigate(destinationId)
    }

    private fun clickEvent() {
        binding.bttnLogin.setOnClickListener { navigateFragment(R.id.action_infoFragment_to_loginFragment) }
        binding.bttnRegister.setOnClickListener { navigateFragment(R.id.action_infoFragment_to_registerFragment) }
    }

    private fun checkLogin(){
        lifecycleScope.launch {
            viewModel.userInfo.collect {
                when (it) {
                    is Resource.Error ->""
                    is Resource.Success ->  navigateToBreakingFragment()
                    Resource.Loading -> {
                    }

                    else -> {}
                }
            }
        }
    }

    private fun navigateToBreakingFragment() {
        findNavController().navigate(R.id.action_infoFragment_to_breakingNewsFragment)
    }

}



