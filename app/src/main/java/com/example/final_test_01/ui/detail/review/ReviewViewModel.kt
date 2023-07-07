package com.example.final_test_01.ui.detail.review

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.data.repository.Repository
import com.example.final_test_01.util.ResponseState
import com.example.final_test_01.util.ResponseState.Loading
import com.example.final_test_01.util.asResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _reviewDto = MutableStateFlow<ResponseState<ReviewDto>>(Loading)
    val reviewDto: StateFlow<ResponseState<ReviewDto>> = _reviewDto

    private val _reviewId = MutableStateFlow<ResponseState<ReviewDto>>(Loading)
    val reviewId: StateFlow<ResponseState<ReviewDto>> = _reviewId

    val review1 = MutableLiveData("")
    val reviewer1 = MutableLiveData("")
    val reviewEmail1 = MutableLiveData("")

    fun createReview(reviewDto: ReviewDto) {
        viewModelScope.launch {
            repository.createReview(reviewDto).asResponseState().collect {
                _reviewDto.value = it
                Log.e(TAG, "_reviewDto -> review: $it")
            }
        }
    }

    fun getReviewById(reviewId: Int) {
        viewModelScope.launch {
            repository.getReviewById(reviewId).asResponseState().collect {
                _reviewId.value = it
            }
        }
    }

    fun updateReview(reviewId: Int) {
        val review = ReviewDto(
            reviewId = reviewId,
            review = review1.value,
            reviewer = reviewer1.value,
            reviewerEmail = reviewEmail1.value
        )
        viewModelScope.launch {
            repository.updateReview(reviewId, review).asResponseState().collect{
                _reviewDto.value = it
            }
        }
    }

    fun checkInput() =
        !(review1.value!!.isEmpty() || reviewEmail1.value!!.isEmpty() || reviewer1.value!!.isEmpty())

}