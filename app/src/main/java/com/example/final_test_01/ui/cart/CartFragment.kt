package com.example.final_test_01.ui.cart

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
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentCartBinding
import com.example.final_test_01.ui.cart.adapter.CartAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {
    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var adapter: CartAdapter
    private val args: CartFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()
    }

    private fun setUi() {
        createCartAdapter()
        observeAddItemsToCart()
        hideSearchView()
    }

    private fun createCartAdapter() {
        adapter = CartAdapter()
        viewModel.getItemsIdsProducts(args.cartId)
//        adapter = CartAdapter(
//            onClick = {
//                navController.navigate(
//                    CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(it)
//                )
//            })
        binding.recyclerViewCart.adapter = adapter
    }

    private fun observeAddItemsToCart() {
        viewModel.product.observe(viewLifecycleOwner) {
            it.forEach { id -> viewModel.getItemsIdsProducts(id.productId) }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartList.collect {
                    when (it) {
                        is ResponseState.Error -> {
                            errorToast()
                        }

                        ResponseState.Loading -> {
                            loadingVisibility()
                        }

                        is ResponseState.Success -> {
                            succeedVisibility()
                            val x = viewModel.addItemToList(it.data)
                            adapter.submitList(x)
                        }
                    }
                }
            }
        }
    }

    private fun succeedVisibility() {
        binding.linearLayoutCart.visibility = View.VISIBLE
        binding.animationViewCart.visibility = View.INVISIBLE
        Toast.makeText(requireContext(), "انجام شد", Toast.LENGTH_SHORT).show()
    }

    private fun loadingVisibility() {
        binding.linearLayoutCart.visibility = View.INVISIBLE
        binding.animationViewCart.visibility = View.VISIBLE
    }

    private fun errorToast() {
        Toast.makeText(
            requireContext(),
            "مشکل در اتصال به شبکه",
            Toast.LENGTH_SHORT
        ).show()
    }


    private fun hideSearchView() {
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.GONE
    }

}