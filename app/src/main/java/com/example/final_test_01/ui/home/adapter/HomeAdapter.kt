package com.example.final_test_01.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.databinding.ItemAdapterBinding
import com.example.final_test_01.util.formatPrice

class HomeAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsItemsDto, HomeAdapter.MyViewHolder>(diffUtil) {
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

    inner class MyViewHolder(val binding: ItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
                    onClick(getItem(absoluteAdapterPosition).id)
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
        val item: ProductsItemsDto = getItem(position)
        holder.apply {
            binding.tvAdapter.text = item.name
            item.images.let {
                if (item.price != null) {
                    binding.tvPrice.text = item.price + " تومان"
                }
                if (it.isNotEmpty()) {
                    Glide.with(binding.root)
                        .load(item.images[0].src)
                        .into(binding.imageViewAdapter)
                }

            }
        }
    }
}