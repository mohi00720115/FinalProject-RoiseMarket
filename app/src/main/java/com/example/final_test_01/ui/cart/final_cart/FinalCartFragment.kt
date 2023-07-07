package com.example.final_test_01.ui.cart.final_cart

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentFinalCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalCartFragment : Fragment(R.layout.fragment_final_cart) {
    private lateinit var binding: FragmentFinalCartBinding
    private val viewModel: FinalCartViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        navController = findNavController()

    }
}