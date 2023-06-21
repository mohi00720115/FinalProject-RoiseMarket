package com.example.final_test_01.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentCustomerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerFragment : Fragment(R.layout.fragment_customer) {
    private lateinit var binding: FragmentCustomerBinding
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        navController = findNavController()
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.GONE

        binding.btnSignUp.setOnClickListener {
            viewModel.createCustomerObject()
        }

    }
}