package com.example.final_test_01.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.dto.product_category.ProductsCategoryItemsDto
import com.example.final_test_01.databinding.CartItemAdapterBinding

class CartAdapter(
//    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsCategoryItemsDto, CartAdapter.MyViewHolder>(diffUtil) {
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

    inner class MyViewHolder(val binding: CartItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
//                    getItem(absoluteAdapterPosition).id?.let { id -> onClick(id) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        return MyViewHolder(
            CartItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
        val item: ProductsCategoryItemsDto = getItem(position)
        holder.apply {
            Glide.with(binding.root)
                .load(item.image?.src)
                .into(binding.imageViewCartItem)
            binding.tvProductNameCartItem.text= item.name
            binding.tvNumberOfProductCartItem.text= item.count.toString()
        }
    }
}