package com.muzo.newsapp.feature.fragment.userFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muzo.newsapp.R
import com.muzo.newsapp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private val viewModel: FragmentUserViewModel by viewModels()
    private lateinit var builder: AlertDialog.Builder


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)


        clickListener()
        infoUserEvent()
        return binding.root
    }

    private fun clickListener() {

        binding.logOutButton.setOnClickListener {
            alertDialog()

        }
    }


    private fun alertDialog() {
        builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Log Out")
            .setMessage("Do you want to Log Out İf You admit this your saved list canceled")
            .setCancelable(true).setIcon(R.drawable.ic_user)
            .setNegativeButton("NO") { dialogInterface, _ -> }
            .setPositiveButton("OK") { dialogInterface, _ ->

                lifecycleScope.launch {
                    viewModel.logOut()
                    viewModel.deleteAllSavedNews()
                    navigateToLoginFragment()

                }

            }.show()
    }

    private fun navigateToLoginFragment() {

        findNavController().navigate(R.id.action_userFragment_to_infoFragment)

    }

    private fun infoUserEvent() {

        lifecycleScope.launch {
            val userName = viewModel?.currentUser?.displayName
            binding.userName.text = userName

            val userMail = viewModel?.currentUser?.email
            binding.userMail.text = userMail
        }

    }

}