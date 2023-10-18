package com.muzo.newsapp.feature.fragment.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muzo.newsapp.R
import com.muzo.newsapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.loginButton.setOnClickListener {
            val email = binding.etMail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            }
        }

        return binding.root

    }

    private fun login(email: String, password: String) {
        lifecycleScope.launch {
            viewModel.login(email, password, {
                // İşlem başarılı olduğunda yapılacaklar
                navigateToAnotherFragment()
            }, { e ->
                // Hata durumunda yapılacaklar
                // Hata mesajını gösterebilirsiniz
            })
        }
    }

    private fun navigateToAnotherFragment(){
        findNavController().navigate(R.id.action_loginFragment_to_breakingNewsFragment)
    }

}