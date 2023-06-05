package com.example.final_test_01.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentHomeBinding
import com.example.final_test_01.ui.home.paging.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterNewest: HomeAdapter
    private lateinit var adapterMostVisited: HomeAdapter
    private lateinit var adapterTopRated: HomeAdapter
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        navController = findNavController()
        setUi()
        observer()
    }

    private fun setUi() {
        createNewestAdapter()
        createMostVisitedAdapter()
        createTopRatedAdapter()
    }

    private fun createNewestAdapter() {
        adapterNewest = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(id)
            )
            Log.e(TAG, "createNewestAdapter: $id")
        })
        binding.recyclerNewsProducts.adapter = adapterNewest
    }

    private fun createMostVisitedAdapter() {
        adapterMostVisited = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(id)
            )
        })
        binding.recyclerMostVisitedProducts.adapter = adapterMostVisited
    }

    private fun createTopRatedAdapter() {
        adapterTopRated = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(id)
            )
        })
        binding.recyclerTopRateProducts.adapter = adapterTopRated
    }

    private fun observer() {
        viewModel.newestProduct.observe(viewLifecycleOwner) {
            adapterNewest.submitList(it)
            Log.e(TAG, "setUiIT: ${it}")
        }

        viewModel.mostVisitedProduct.observe(viewLifecycleOwner) {
            adapterMostVisited.submitList(it)
            Log.e(TAG, "setUi: ${it[0]}")
        }

        viewModel.topRatedProduct.observe(viewLifecycleOwner) {
            adapterTopRated.submitList(it)
            Log.e(TAG, "setUi: ${it[0]}")
        }
    }

}