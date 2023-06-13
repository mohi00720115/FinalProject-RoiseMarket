package com.example.final_test_01.ui.activity.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.product_dto.ProductsItemsDto
import com.example.final_test_01.databinding.SearchItemAdapterBinding

class SearchAdapter(
//    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsItemsDto, SearchAdapter.MyViewHolder>(diffUtil) {
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

    inner class MyViewHolder(val binding: SearchItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener {
//                    onClick(getItem(absoluteAdapterPosition).id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SearchItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: ProductsItemsDto = getItem(position)
        holder.apply {
            with(binding) {
                Glide.with(binding.root)
                    .load(item.images[0].src)
                    .into(imageViewSearchItem)
                tvProductNameSearchItem.text = item.name
                tvPriceSearchItem.text = item.price+" تومان"
            }

        }
    }
}