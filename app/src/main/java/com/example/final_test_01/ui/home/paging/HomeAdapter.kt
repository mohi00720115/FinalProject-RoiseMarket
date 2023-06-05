package com.example.final_test_01.ui.home.paging

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import com.example.final_test_01.databinding.ItemAdapterBinding

class HomeAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsUiStateItem, HomeAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductsUiStateItem>() {
            override fun areItemsTheSame(
                oldItem: ProductsUiStateItem,
                newItem: ProductsUiStateItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProductsUiStateItem,
                newItem: ProductsUiStateItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }

    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
                    onClick(getItem(absoluteAdapterPosition).id)
                    Log.e(TAG, "absoluteAdapterPosition: ${getItem(absoluteAdapterPosition).id}")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.MyViewHolder {
        return MyViewHolder(
            ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeAdapter.MyViewHolder, position: Int) {
        val item: ProductsUiStateItem = getItem(position)
        holder.apply {
            binding.tvAdapter.text = item.name
            binding.tvPrice.text = "${item.price} تومان"
            Glide.with(binding.root)
                .load(item.images[0].src)
                .into(binding.imageViewAdapter)
        }
    }
}