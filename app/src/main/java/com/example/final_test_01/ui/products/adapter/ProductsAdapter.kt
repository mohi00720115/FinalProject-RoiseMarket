package com.example.final_test_01.ui.products.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.databinding.ProductItemAdapterBinding

class ProductsAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsItemsDto, ProductsAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductsItemsDto>() {
            override fun areItemsTheSame(
                oldItem: ProductsItemsDto,
                newItem: ProductsItemsDto
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProductsItemsDto,
                newItem: ProductsItemsDto
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    inner class MyViewHolder(val binding: ProductItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
                    onClick(getItem(absoluteAdapterPosition).id)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsAdapter.MyViewHolder {
        return MyViewHolder(
            ProductItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsAdapter.MyViewHolder, position: Int) {
        val item: ProductsItemsDto = getItem(position)
        holder.apply {
            item.images.let {
                if (it.isNotEmpty()) {
                    Glide.with(binding.root)
                        .load(item.images[0].src)
                        .into(binding.imageViewProductAdapter)
                    binding.tvProductAdapter.text = item.name
                    binding.tvProductPrice.text = item.price
                }
            }
        }
    }
}