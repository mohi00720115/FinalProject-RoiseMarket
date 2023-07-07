package com.example.final_test_01.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_test_01.R
import com.example.final_test_01.data.model.dto.order.LineItem
import com.example.final_test_01.databinding.CartItemAdapterBinding
import com.example.final_test_01.util.formatPrice
import com.example.final_test_01.util.showImageByGlide

class CartAdapter(
    private val onClick: (Int, Int, Int) -> Unit
) : ListAdapter<LineItem, CartAdapter.MyViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LineItem>() {
            override fun areItemsTheSame(
                oldItem: LineItem,
                newItem: LineItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LineItem,
                newItem: LineItem
            ): Boolean {
                return oldItem.packageId == newItem.packageId
            }

        }
    }

    inner class MyViewHolder(val binding: CartItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*init {
            binding.root.apply {
                setOnClickListener {
//                    getItem(absoluteAdapterPosition).id?.let { id -> onClick(id) }
                }
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        return MyViewHolder(
            CartItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
        val item: LineItem = getItem(position)
        var quantity = 1
        holder.apply {
            with(binding) {
                //وقتی تعداد محصول به 1 میرسه علامت سطل آشغال نمایش داده میشه
                if (item.quantity == 1) tvMinusProductCart.setIconResource(R.drawable.trash)
                else tvMinusProductCart.setIconResource(R.drawable.minus)

                item.image.let {
                    if (it.src.isNotEmpty()) {
                        binding.imageViewCartItem.showImageByGlide(item.image.src)
                    }
                }
                tvProductNameCartItem.text = item.name
                tvPriceCartItem.text = item.price?.toDouble()?.formatPrice() + " تومان"

                tvAddProductCart.setOnClickListener {
                    quantity++
                    tvNumberProductCart.text = (quantity).toString()
                    onClick(item.packageId!!,item.quantity!!,item.productId!!)
                }

                tvMinusProductCart.setOnClickListener {
                    quantity--
                    tvNumberProductCart.text = (quantity).toString()
                    onClick(item.packageId!!,item.quantity!!,item.productId!!)
                }
            }
        }
    }
}