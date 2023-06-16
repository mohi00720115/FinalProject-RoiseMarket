package com.example.final_test_01.ui.customer

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.DetailDialogBinding
import com.example.final_test_01.databinding.FragmentCartBinding
import com.example.final_test_01.databinding.FragmentCustomerBinding
import com.example.final_test_01.ui.dialog_detail_items.DetailDialogViewModel
import com.example.final_test_01.ui.dialog_detail_items.DetailsDialogFragmentArgs
import com.example.final_test_01.ui.dialog_detail_items.adapter.DetailItemsAdapter
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

        binding.btnSignUp.setOnClickListener{
            viewModel.createCustomerObject()
        }
    }
}