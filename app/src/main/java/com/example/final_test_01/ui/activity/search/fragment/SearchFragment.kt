package com.example.final_test_01.ui.activity.search.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentSearchBinding
import com.example.final_test_01.ui.activity.search.adapter.SearchAdapter
import com.example.final_test_01.util.ResponseState
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter
    private lateinit var searchView: SearchView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        searchView = binding.searchViewFragment
        observeSearch(binding.searchViewFragment)
        setUi()
        viewModel.query.observe(viewLifecycleOwner) {
            viewModel.getCategoriesByIds()
            Log.e(TAG, "getCategoriesByIds: $it")
        }
    }

    private fun setUi() {
        observeSearch(searchView)
        createSearchAdapter()
        clickOnTvSearches()
    }

    private fun createSearchAdapter() {
        adapter = SearchAdapter()
        binding.recyclerViewSearch.adapter = adapter
    }

    private fun observeSearch(searchView: SearchView) {
        viewModel.search.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> Toast.makeText(
                    requireContext(),
                    "مشکل در اتصال به شبکه",
                    Toast.LENGTH_SHORT
                ).show()

                is ResponseState.Success -> {
                    adapter.submitList(it.data)
                    Log.e(TAG, "it.data: ${it.data}")
                }

                ResponseState.Loading -> {

                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getAllProductsForSearch(query!!)
//                viewModel.getCategoriesByIds(1,15, query)   //دقت
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun clickOnTvSearches() {
        val clickForTvSearches = arrayListOf<Chip>(
            binding.rbSearchDate,
            binding.rbSearchPopularity,
            binding.rbSearchPrice,
            binding.rbSearchRating,
        )
        for (i in clickForTvSearches) {
            i.setOnClickListener {
                viewModel.changeOrderBy(i.tag.toString())
                Log.e(TAG, "clickOnTvSearches: ${i.tag}")
            }
        }
    }


}