package com.example.final_test_01.ui.detail.review.adapter

import android.annotation.SuppressLint
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_test_01.data.model.dto.review.ReviewDto
import com.example.final_test_01.databinding.ReviewItemAdapterBinding

class ReviewAdapter(
    private val onClick: (Int) -> Unit,
    private val onClickDelete: (Int, Int) -> Unit,
) : ListAdapter<ReviewDto, ReviewAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ReviewDto>() {
            override fun areItemsTheSame(
                oldItem: ReviewDto,
                newItem: ReviewDto
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ReviewDto,
                newItem: ReviewDto
            ): Boolean {
                return oldItem.reviewId == newItem.reviewId
            }

        }
    }

    inner class MyViewHolder(val binding: ReviewItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
                    getItem(absoluteAdapterPosition).reviewId?.let { id -> onClick(id) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ReviewItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: ReviewDto = getItem(position)
        holder.apply {
            binding.tvReviewStatus.text = "نام خریدار: ${item.reviewer}"
            val review: Spanned =
                HtmlCompat.fromHtml(item.review.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.tvReviewReviewer.text = review
            binding.tvReviewDate.text = item.dateCreated
            binding.tvDeleteReview.setOnClickListener {
                item.reviewId?.let { it1 -> onClickDelete(it1,absoluteAdapterPosition) }
            }
        }
    }

}