package com.example.final_test_01.ui.cart

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat
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
import com.example.final_test_01.ui.category.CategoryFragmentDirections
import com.example.final_test_01.ui.dialog_detail_items.DetailsDialogFragmentArgs
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
        observeItemId()
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

    @SuppressLint("SetTextI18n")
    private fun observeItemId() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartList.collect {
                    with(binding) {
                        when (it) {
                            is ResponseState.Error -> Toast.makeText(
                                requireContext(),
                                "مشکل در اتصال به شبکه",
                                Toast.LENGTH_SHORT
                            ).show()

                            is ResponseState.Success -> {
                                binding.linearLayoutCart.visibility = View.VISIBLE
                                binding.progressBarCart.visibility = View.INVISIBLE
                                tvCartPrice.text = it.data[0].id.toString()
                                Log.e(TAG, "tvCartPrice: ${it.data[0].id}")
                                tvCartPriceFee.text = "${it.data[0].price} تومان"
                                Log.e(TAG, "tvCartPriceFee: ${it.data[0].price}")
//                        val description: Spanned = HtmlCompat.fromHtml(it.data[0].description.toString(),
//                            HtmlCompat.FROM_HTML_MODE_LEGACY)
//                        tvDescriptionDialog.text = description
//                        adapter.submitList(it.data[0].images)
                            }

                            ResponseState.Loading -> {
                                binding.linearLayoutCart.visibility = View.INVISIBLE
                                binding.progressBarCart.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

}