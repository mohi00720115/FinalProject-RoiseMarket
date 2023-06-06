package com.example.final_test_01.ui.category.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.product_category_dto.ProductsCategoryItemsDto
import com.example.final_test_01.databinding.CategoryItemAdapterBinding

class CategoryAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsCategoryItemsDto, CategoryAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductsCategoryItemsDto>() {
            override fun areItemsTheSame(
                oldItem: ProductsCategoryItemsDto,
                newItem: ProductsCategoryItemsDto
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProductsCategoryItemsDto,
                newItem: ProductsCategoryItemsDto
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    inner class MyViewHolder(val binding: CategoryItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
                    getItem(absoluteAdapterPosition).id?.let { id -> onClick(id) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.MyViewHolder {
        return MyViewHolder(
            CategoryItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryAdapter.MyViewHolder, position: Int) {
        val item: ProductsCategoryItemsDto = getItem(position)
        holder.apply {
            Glide.with(binding.root)
                .load(item.image?.src)
                .into(binding.imageViewCategory)
            binding.tvCategory.text= item.name
            Log.e(TAG, "ProductsCategoryUiStateItem: ${item.image?.src}")
        }
    }
}