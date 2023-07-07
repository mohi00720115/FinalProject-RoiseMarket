package com.example.final_test_01.ui.detail

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
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
import com.example.final_test_01.R.layout.detail_dialog
import com.example.final_test_01.databinding.DetailDialogBinding
import com.example.final_test_01.ui.detail.adapter.DetailItemsAdapter
import com.example.final_test_01.ui.detail.review.adapter.ReviewAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(detail_dialog) {
    private lateinit var binding: DetailDialogBinding
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var navController: NavController
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var adapter: DetailItemsAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()
        binding.btnAddToReview.setOnClickListener {
            navController.navigate(
                DetailsFragmentDirections.actionDetailsDialogToReviewFragment(args.productsId)
            )
        }
    }


    private fun setUi() {
        createItemIdAdapter()
        observeItemId()
        createReviewAdapter()
        observeReviewList()
        observeAddToCart()
        hideSearchView()
    }

    private fun createItemIdAdapter() {
        adapter = DetailItemsAdapter()
        viewModel.getItemsIdsProducts(args.productsId)
        binding.recyclerViewGallery.adapter = adapter
    }


    /**
     * با کلیک روی افزودن به سبد خرید، محصول به سبد خرید اضافه میشه
     */
    private fun observeAddToCart() {
        binding.btnAddToCart.setOnClickListener {
            viewModel.getOrderById()
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.orderById.collect {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                viewModel.putUpdateOrder(args.productsId)
                                Log.e(TAG, "observeAddToCart 'ORDER_ID' ${it.data.id}")
                            }
                        }
                    }

                }
            }
            Toast.makeText(requireContext(), "محصول به سبد خرید شما اضافه شد", Toast.LENGTH_SHORT)
                .show()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun observeItemId() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemID.collect {
                    with(binding) {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                tvTitleDialog.text = it.data[0].name
                                tvPriceDialog.text =
                                    it.data[0].price + " تومان"
                                val description: Spanned = HtmlCompat.fromHtml(
                                    it.data[0].description.toString(),
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                tvDescriptionDialog.text = description
                                adapter.submitList(it.data[0].images)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeReviewList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reviewList.collect {
                    with(binding) {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                reviewAdapter.submitList(it.data)
                            }
                        }
                    }
                }
            }

        }
    }

    private fun createReviewAdapter() {
        reviewAdapter = ReviewAdapter(
            onClick = {
            navController.navigate(
                DetailsFragmentDirections.actionDetailsDialogToReviewFragment(it)
            )
        },
            onClickDelete = { reviewId, adapter ->
                viewModel.deleteReview(reviewId)
                reviewAdapter.notifyItemRemoved(adapter)
            })
        viewModel.getProductReviewList(args.productsId)
        binding.recyclerViewReviewProduct.adapter = reviewAdapter
    }

    private fun loadingVisibility() {
        binding.clDetail2.visibility = View.INVISIBLE
        binding.animationViewDetail.visibility = View.VISIBLE
    }

    private fun succeedVisibility() {
        binding.clDetail2.visibility = View.VISIBLE
        binding.animationViewDetail.visibility = View.INVISIBLE
    }

    private fun errorToast() {
        Toast.makeText(requireContext(), "مشکل در اتصال به شبکه", Toast.LENGTH_SHORT).show()
    }

    private fun hideSearchView() {
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.GONE
    }

}