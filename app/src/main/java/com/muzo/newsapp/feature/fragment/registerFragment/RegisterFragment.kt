package com.muzo.newsapp.feature.fragment.registerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muzo.newsapp.R
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        observeData()
        clickEvent()


        return binding.root
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.signUpFlow.collect {
                when (it) {
                    is Resource.Error -> toastMessage(it.exception?.message!!)
                    is Resource.Success -> navigateFragment()
                    Resource.Loading -> {
                        binding.etName.visibility = View.VISIBLE
                    }

                    else -> {}
                }
            }
        }
    }

    private fun clickEvent() {
        binding.signUpButton.setOnClickListener {
            val name=binding.etName.text.toString()
            val email = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etconfirmPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (confirmPassword == password) {
                    viewModel.signUp(name,email,password)
                }else{
                    toastMessage("Password does not match")
                }
            }else{
                toastMessage("Name and password cannot be empty")
            }
        }
    }

    private fun navigateFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_breakingNewsFragment)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}