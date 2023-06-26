package com.example.final_test_01.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spanned
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.data.local.LineItemEntity
import com.example.final_test_01.databinding.DetailDialogBinding
import com.example.final_test_01.ui.detail.adapter.DetailItemsAdapter
import com.example.final_test_01.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsDialogFragment : Fragment(R.layout.detail_dialog) {
    private lateinit var binding: DetailDialogBinding
    private val viewModel: DetailDialogViewModel by viewModels()
    private lateinit var navController: NavController
    private val args: DetailsDialogFragmentArgs by navArgs()
    private lateinit var adapter: DetailItemsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUi()
        setOnClickBtnAddToCart()
    }

    private fun setOnClickBtnAddToCart() {
        binding.btnAddToCart.setOnClickListener {
            viewModel.insert(LineItemEntity(args.detailItems))
            Toast.makeText(requireContext(),"محصول به سبد خرید شما اضافه شد",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUi() {
        createItemIdAdapter()
        observeItemId()
    }

    private fun createItemIdAdapter() {
        adapter = DetailItemsAdapter()
        viewModel.getItemsIdsProducts(args.detailItems)
        binding.recyclerViewGallery.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun observeItemId() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemID.collect {
                    with(binding) {
                        when (it) {
                            is ResponseState.Error -> Toast.makeText(
                                requireContext(),
                                "مشکل در اتصال به شبکه",
                                Toast.LENGTH_SHORT
                            ).show()

                            is ResponseState.Success -> {
                                binding.clDetail2.visibility = View.VISIBLE
                                binding.animationViewDetail.visibility = View.INVISIBLE
                                tvTitleDialog.text = it.data[0].name
                                tvPriceDialog.text = "${it.data[0].price} تومان"
                                val description: Spanned = HtmlCompat.fromHtml(
                                    it.data[0].description.toString(),
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                tvDescriptionDialog.text = description
                                adapter.submitList(it.data[0].images)
                            }

                            ResponseState.Loading -> {
                                binding.clDetail2.visibility = View.INVISIBLE
                                binding.animationViewDetail.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

}