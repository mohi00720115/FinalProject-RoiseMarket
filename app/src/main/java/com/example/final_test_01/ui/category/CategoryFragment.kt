package com.example.final_test_01.ui.category

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
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentCategoryBinding
import com.example.final_test_01.ui.category.adapter.CategoryAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: CategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()
    }

    private fun setUi() {
        createCategoryAdapter()
        observeCategory()
        showSearchView()
    }

    private fun createCategoryAdapter() {
        adapter = CategoryAdapter(
            onClick = {
                navController.navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(it)
                )
            })
        viewModel.getAllCategories()
        binding.recyclerViewCategory.adapter = adapter
    }

    private fun observeCategory() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.category.collect {
                    when (it) {
                        is ResponseState.Error -> {
                            errorToast()
                        }

                        is ResponseState.Success -> {
                            succeedVisibility()
                            adapter.submitList(it.data)
                        }

                        ResponseState.Loading -> {
                            loadingVisibility()
                        }
                    }
                }
            }
        }
    }

    private fun errorToast() {
        Toast.makeText(requireContext(),"مشکل در اتصال به شبکه",Toast.LENGTH_SHORT).show()
    }

    private fun loadingVisibility() {
        binding.recyclerViewCategory.visibility = View.INVISIBLE
        binding.animationViewCategory.visibility = View.VISIBLE
    }

    private fun succeedVisibility() {
        binding.recyclerViewCategory.visibility = View.VISIBLE
        binding.animationViewCategory.visibility = View.INVISIBLE
    }

    private fun showSearchView() {
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.VISIBLE
    }

}