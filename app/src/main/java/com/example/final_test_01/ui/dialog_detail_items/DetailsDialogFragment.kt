package com.example.final_test_01.ui.dialog_detail_items

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_test_01.R
import com.example.final_test_01.databinding.DetailDialogBinding
import com.example.final_test_01.ui.dialog_detail_items.adapter.DetailItemsAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsDialogFragment : BottomSheetDialogFragment(R.layout.detail_dialog) {
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

    }

    private fun setUi() {
        adapter = DetailItemsAdapter()
        viewModel.getIdItemsProducts(args.detailItems)
        Log.e(TAG, "args.detailItems: ${args.detailItems}")
        viewModel.itemID.observe(viewLifecycleOwner) {
            with(binding) {
                tvTitleDialog.text = it[0].name
                tvPriceDialog.text = "${it[0].price} تومان"
                tvDescriptionDialog.text = it[0].description
                recyclerViewGallery.adapter = adapter
                adapter.submitList(it[0].images)
            }
        }
    }
}