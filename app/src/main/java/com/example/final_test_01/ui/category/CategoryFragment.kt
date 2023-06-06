package com.example.final_test_01.ui.category

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.DetailDialogBinding
import com.example.final_test_01.databinding.FragmentCategoryBinding
import com.example.final_test_01.ui.category.adapter.CategoryAdapter
import com.example.final_test_01.ui.dialog_detail_items.DetailDialogViewModel
import com.example.final_test_01.ui.dialog_detail_items.DetailsDialogFragmentArgs
import com.example.final_test_01.ui.dialog_detail_items.adapter.DetailItemsAdapter
import com.example.final_test_01.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: CategoryAdapter
    //    private val args: DetailsDialogFragmentArgs by navArgs()
//    private lateinit var adapter: DetailItemsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()

    }

    private fun setUi() {
        adapter = CategoryAdapter(
            onClick =  {
                navController.navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(it)
                )
                Log.e(TAG, "CategoryFragmentDirections: $it")
            })

        viewModel.getProductsCategories()
        viewModel.category.observe(viewLifecycleOwner) {
            with(binding) {
                recyclerViewCategory.adapter = adapter
                adapter.submitList(it)
                Log.e(TAG, "recyclerViewCategory: $it")
            }
        }
    }
}