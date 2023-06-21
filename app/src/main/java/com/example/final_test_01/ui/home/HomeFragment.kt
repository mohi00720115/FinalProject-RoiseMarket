package com.example.final_test_01.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentHomeBinding
import com.example.final_test_01.ui.home.adapter.HomeAdapter
import com.example.final_test_01.ui.home.adapter.ViewPagerAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapterNewest: HomeAdapter
    private lateinit var adapterMostVisited: HomeAdapter
    private lateinit var adapterTopRated: HomeAdapter
    private lateinit var navController: NavController
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var imageList: MutableList<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        navController = findNavController()
        setUi()
    }

    private fun setUi() {
        createAdapters()
        observers()
        showSearchView()
    }

    private fun createAdapters() {
        createNewestAdapter()
        createMostVisitedAdapter()
        createTopRatedAdapter()
        setUpViewPager()
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topRatedProduct.collect {
                    when (it) {
                        is ResponseState.Error -> Toast.makeText(
                            requireContext(),
                            "مشکل در اتصال به شبکه",
                            Toast.LENGTH_SHORT
                        ).show()

                        is ResponseState.Success -> {
                            adapterTopRated.submitList(it.data)
                            binding.animationViewHome.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.INVISIBLE
                        }

                        ResponseState.Loading -> {
                            binding.nestedScrollView.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun mostVisitedProductObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mostVisitedProduct.collect {
                    when (it) {
                        is ResponseState.Error -> Toast.makeText(
                            requireContext(),
                            "مشکل در اتصال به شبکه",
                            Toast.LENGTH_SHORT
                        ).show()

                        is ResponseState.Success -> {
                            adapterMostVisited.submitList(it.data)
                            binding.animationViewHome.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.INVISIBLE
                        }

                        ResponseState.Loading -> {
                            binding.nestedScrollView.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun newestProductObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newestProduct.collect {
                    when (it) {
                        is ResponseState.Error -> Toast.makeText(
                            requireContext(),
                            "مشکل در اتصال به شبکه",
                            Toast.LENGTH_SHORT
                        ).show()

                        is ResponseState.Success -> {
                            adapterNewest.submitList(it.data)
                            binding.animationViewHome.visibility = View.INVISIBLE
                            binding.nestedScrollView.visibility = View.VISIBLE
                        }

                        ResponseState.Loading -> {
                            binding.nestedScrollView.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun setUpViewPager() {
        viewModel.getIdsCategoryForViewPager()
        viewPager = binding.viewPagerHome
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onSellProducts.collect {
                    when (it) {
                        is ResponseState.Error -> Toast.makeText(
                            requireContext(),
                            "مشکل در اتصال به شبکه",
                            Toast.LENGTH_SHORT
                        ).show()

                        is ResponseState.Success -> {
                            imageList = mutableListOf()
                            for (i in it.data) {
                                for (j in i.images) {
                                    imageList.add(j.src!!)
                                }
                            }
                            viewPagerAdapter = ViewPagerAdapter(requireContext(), imageList.take(7))
                            viewPager = binding.viewPagerHome
                            viewPager.adapter = viewPagerAdapter
                        }

                        ResponseState.Loading -> {
                            binding.nestedScrollView.visibility = View.INVISIBLE
                            binding.animationViewHome.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

    }
    private fun showSearchView() {
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.VISIBLE
    }

}