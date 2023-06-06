package com.example.final_test_01.ui.dialog_detail_items.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.product_dto.Image
import com.example.final_test_01.data.model.product_dto.ProductsUiStateItem
import com.example.final_test_01.databinding.DetailItemsAdapterBinding
import com.example.final_test_01.databinding.ItemAdapterBinding

class DetailItemsAdapter(
//    private val onClick: (Int) -> Unit
) : ListAdapter<Image, DetailItemsAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(
                oldItem: Image,
                newItem: Image
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Image,
                newItem: Image
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    inner class MyViewHolder(val binding: DetailItemsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.root.apply {
//                setOnClickListener {
//                    getItem(absoluteAdapterPosition).id?.let { id -> onClick(id) }
//                }
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailItemsAdapter.MyViewHolder {
        return MyViewHolder(
            DetailItemsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailItemsAdapter.MyViewHolder, position: Int) {
        val item: Image = getItem(position)
        holder.apply {
            Glide.with(binding.root)
                .load(item.src)
                .into(binding.imageViewGalleryAdapter)
            Log.d(TAG, "item.images: ${item.src}")
        }
    }
}