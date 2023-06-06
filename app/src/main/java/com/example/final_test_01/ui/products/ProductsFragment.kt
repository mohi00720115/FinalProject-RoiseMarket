package com.example.final_test_01.ui.products

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentProductsBinding
import com.example.final_test_01.ui.products.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: ProductsAdapter
    private val args: ProductsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()
    }

    private fun setUi() {
        adapter = ProductsAdapter(onClick = {
            navController.navigate(
                ProductsFragmentDirections.actionProductsFragmentToDetailsDialog(it)
            )
        })
        viewModel.getProductsByCategories(args.productIdItem.toString())
        binding.recyclerViewProducts.adapter = adapter
        viewModel.productCategory.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}