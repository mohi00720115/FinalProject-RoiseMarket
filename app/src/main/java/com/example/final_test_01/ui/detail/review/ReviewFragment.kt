package com.example.final_test_01.ui.detail.review

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.databinding.FragmentReviewBinding
import com.example.final_test_01.ui.detail.review.adapter.ReviewAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewFragment : Fragment(R.layout.fragment_review) {
    private lateinit var binding: FragmentReviewBinding
    private val viewModel: ReviewViewModel by viewModels()
    private lateinit var navController: NavController
    private val args: ReviewFragmentArgs by navArgs()
    private lateinit var adapter: ReviewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        navController = findNavController()
        createReview(args.reviewId)
//        viewModel.getReviewById(args.reviewId)
//        getReviewById()
        Log.e(TAG, "args: ${args.reviewId}")
//        binding.btnSubmitReview.setOnClickListener {
//            viewModel.updateReview(args.reviewId)
//            Log.e(TAG, "onClick: ${args.reviewId}")
            /*viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.reviewDto.collect {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                Log.e(TAG, "data.reviewId: ${it.data.review}")
                                navController.navigate(
                                    ReviewFragmentDirections.actionReviewFragmentToDetailsDialog(
                                        args.reviewId
                                    )
                                )
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }*/
//        }
    }

        private fun createReview(id: Int) {
            binding.btnSubmitReview.setOnClickListener {
                if (!viewModel.checkInput()) {
                    Toast.makeText(requireContext(), "تمام فیلدها را پر کنید", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val email = binding.etEmailReview.text.toString()
                    val review = binding.etDescriptionReview.text.toString()
                    val reviewer = binding.etNameReview.text.toString()

                    viewModel.createReview(
                        ReviewDto(
                            productId = id,
                            reviewer = reviewer,
                            review = review,
                            reviewerEmail = email
                        )
                    )
                    Log.e(TAG, "RevFrag putUpdateOrder -> ${args.reviewId}")
                    observeReview()
                    navController.navigate(
                        ReviewFragmentDirections.actionReviewFragmentToDetailsDialog(args.reviewId)
                    )
                    navController.popBackStack()
                }
            }
        }

        private fun observeReview() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.reviewDto.collect {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                navController.popBackStack()
                                Log.e(TAG, "Success: ${it.data}")
                            }
                        }
                    }
                }
            }
        }

    private fun getReviewById() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reviewId.collect {
                    when (it) {
                        is ResponseState.Error -> {
//                            errorToast()
                        }

                        ResponseState.Loading -> {
//                            loadingVisibility()
                        }

                        is ResponseState.Success -> {
//                            succeedVisibility()
                            binding.etNameReview.setText(it.data.reviewer)
                            binding.etDescriptionReview.setText(it.data.review)
                            binding.etEmailReview.setText(it.data.reviewerEmail)
                            Log.e(TAG, "getReviewById: ${it.data.reviewerEmail}")
                        }
                    }
                }
            }
        }
    }

    private fun errorToast() {
        Toast.makeText(requireContext(), "مشکل در اتصال به شبکه", Toast.LENGTH_SHORT).show()
    }

    private fun loadingVisibility() {
        binding.clReview.visibility = View.INVISIBLE
        binding.animationViewReview.visibility = View.VISIBLE
    }

    private fun succeedVisibility() {
        binding.clReview.visibility = View.VISIBLE
        binding.animationViewReview.visibility = View.INVISIBLE
    }


}