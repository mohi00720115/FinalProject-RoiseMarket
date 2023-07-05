package com.example.final_test_01.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_test_01.data.model.dto.product.ProductsItemsDto
import com.example.final_test_01.databinding.CartItemAdapterBinding
import com.example.final_test_01.util.formatPrice

class CartAdapter(
//    private val onClick: (Int) -> Unit
) : ListAdapter<ProductsItemsDto, CartAdapter.MyViewHolder>(diffUtil) {
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
        val item: ProductsItemsDto = getItem(position)
        var count = 1
        holder.apply {
            with(binding) {
                item.images.let {
                    if (it.isNotEmpty()) {
                        Glide.with(binding.root)
                            .load(item.images[0].src)
                            .into(binding.imageViewCartItem)
                        tvProductNameCartItem.text = item.name
                        tvPriceCartItem.text = item.price?.toDouble()?.formatPrice()+" تومان"
                        tvAddProductCart.setOnClickListener {
                            tvNumberProductCart.text = (++count).toString()
                        }
                        tvMinusProductCart.setOnClickListener {
                            tvNumberProductCart.text = (--count).toString()
                        }
                    }
                }
            }
        }
    }
}