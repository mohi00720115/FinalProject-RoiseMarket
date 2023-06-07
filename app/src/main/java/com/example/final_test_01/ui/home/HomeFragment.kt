package com.example.final_test_01.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentHomeBinding
import com.example.final_test_01.ui.home.adapter.HomeAdapter
import com.example.final_test_01.util.ResponseState
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
    }

    private fun setUi() {
        createAdapters()
        observers()
    }

    private fun createAdapters() {
        createNewestAdapter()
        createMostVisitedAdapter()
        createTopRatedAdapter()
    }

    private fun createNewestAdapter() {
        adapterNewest = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(it)
            )
        })
        binding.recyclerNewsProducts.adapter = adapterNewest
    }

    private fun createMostVisitedAdapter() {
        adapterMostVisited = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(it)
            )
        })
        binding.recyclerMostVisitedProducts.adapter = adapterMostVisited
    }

    private fun createTopRatedAdapter() {
        adapterTopRated = HomeAdapter(onClick = {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsDialog(it)
            )
        })
        binding.recyclerTopRateProducts.adapter = adapterTopRated
    }

    private fun observers() {
        newestProductObserve()
        mostVisitedProductObserve()
        topRatedProductObserve()
    }

    private fun topRatedProductObserve() {
        viewModel.topRatedProduct.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    requireContext(),
                    "مشکل در اتصال به شبکه",
                    Toast.LENGTH_SHORT
                ).show()

                is ResponseState.Success -> {
                    adapterTopRated.submitList(it.data)
                }
            }
        }
    }

    private fun mostVisitedProductObserve() {
        viewModel.mostVisitedProduct.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    requireContext(),
                    "مشکل در اتصال به شبکه",
                    Toast.LENGTH_SHORT
                ).show()

                is ResponseState.Success -> {
                    adapterMostVisited.submitList(it.data)
                }
            }
        }
    }

    private fun newestProductObserve() {
        viewModel.newestProduct.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    requireContext(),
                    "مشکل در اتصال به شبکه",
                    Toast.LENGTH_SHORT
                ).show()

                is ResponseState.Success -> {
                    adapterNewest.submitList(it.data)
                }
            }
        }
    }

}