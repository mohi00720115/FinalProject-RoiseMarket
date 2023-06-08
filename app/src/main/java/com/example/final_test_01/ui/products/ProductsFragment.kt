package com.example.final_test_01.ui.products

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentProductsBinding
import com.example.final_test_01.ui.products.adapter.ProductsAdapter
import com.example.final_test_01.util.ResponseState
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
        createProductAdapter()
        productCategoryObserve()
    }

    private fun createProductAdapter() {
        adapter = ProductsAdapter(onClick = {
            navController.navigate(
                ProductsFragmentDirections.actionProductsFragmentToDetailsDialog(it)
            )
        })
        viewModel.getCategoriesByIds(args.productIdItem.toString())
        binding.recyclerViewProducts.adapter = adapter
    }

    private fun productCategoryObserve() {
        viewModel.productCategory.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    requireContext(),
                    "مشکل در اتصال به شبکه",
                    Toast.LENGTH_SHORT
                ).show()

                is ResponseState.Success -> {
                    adapter.submitList(it.data)
                    //ست کردن نام کتگوری ها در تکست ویو
                    binding.tvProducts.text = it.data[0].categories?.get(0)?.name.toString()
                }
            }
        }
    }
}