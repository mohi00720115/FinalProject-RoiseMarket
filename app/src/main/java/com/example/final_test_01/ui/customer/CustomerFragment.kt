package com.example.final_test_01.ui.customer

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentCustomerBinding
import com.example.final_test_01.util.Const.STATUS
import com.example.final_test_01.util.ResponseState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerFragment : Fragment(R.layout.fragment_customer) {
    private lateinit var binding: FragmentCustomerBinding
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        navController = findNavController()
        setUi()
        getOrders()

    }

    private fun getOrders() {
        viewModel.searchByEmail.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Error -> {
                    errorToast()
                }

                ResponseState.Loading -> {
                    loadingVisibility()
                }

                is ResponseState.Success -> {
                    succeedVisibility()
                    if (it.data.isEmpty()) {
                        viewModel.createOrders()
                    } else {
                        val orderId = it.data[0].id
                    }
                    Log.e(TAG, "getOrders: ${it.data}")
                }
            }
        }
    }

    private fun createCustomerOnClick() {
        with(binding) {
            /*viewModel?.customer?.observe(viewLifecycleOwner) {
                when (it) {
                    is ResponseState.Error -> {
                        errorToast()
                    }

                    ResponseState.Loading -> {
                        loadingVisibility()
                    }

                    is ResponseState.Success -> {
                        succeedVisibility()
                    }
                }
            }*/
            btnSignUp.setOnClickListener {
                STATUS = false
                if (viewModel.checkCustomerEntries()) {
                    Toast.makeText(
                        requireContext(),
                        "لطفا تمام اطلاعات رو کامل کنید",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    STATUS = true
                    /*val sharedPreferences = context?.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
                    sharedPreferences?.edit()?.putBoolean("BOOLEAN", STATUS)?.apply()*/
                    viewModel?.getCustomersByEmail()
                    viewModel?.getEmailCustomer?.observe(viewLifecycleOwner) {
                        when (it) {
                            is ResponseState.Error -> {
                                errorToast()
                            }

                            ResponseState.Loading -> {
                                loadingVisibility()
                            }

                            is ResponseState.Success -> {
                                succeedVisibility()
                                if (it.data.isEmpty()) {
                                    viewModel?.createCustomer()
                                } else {
                                    viewModel?.getOrdersByEmail()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUi() {
        createCustomerOnClick()
        hideSearchView()
        setImageAsGallery()
    }

    /**
     * ست کردن عکس از گالری در صفحه ثبت نام
     */
    private fun setImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val imageUri = data?.data
            Glide.with(binding.root)
                .load(imageUri)
                .into(binding.imageView)
        }
    }

    private fun setImageAsGallery() {
        binding.imageView.setOnClickListener { setImage() }
    }

    private fun hideSearchView() {
        requireActivity().findViewById<CardView>(R.id.search_cardView).visibility = View.GONE
    }

    /**
     * نمایش فرگمنت هنگام موفقیت
     */
    private fun succeedVisibility() {
        binding.constraintLayoutCustomer.visibility = View.VISIBLE
        binding.animationViewCustomer.visibility = View.INVISIBLE
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility =
            View.VISIBLE
        Toast.makeText(
            requireContext(),
            "ثبت نام با موفقیت انجام شد",
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * مخفی کردن فرگمنت هنگام لودینگ
     */
    private fun loadingVisibility() {
        binding.constraintLayoutCustomer.visibility = View.INVISIBLE
        binding.animationViewCustomer.visibility = View.VISIBLE
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility =
            View.GONE
    }

    /**
     * نمایش پیام هنگام ارور شبکه
     */
    private fun errorToast() {
        binding.constraintLayoutCustomer.visibility = View.VISIBLE
        binding.animationViewCustomer.visibility = View.INVISIBLE
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility =
            View.VISIBLE
        Toast.makeText(
            requireContext(),
            "اطلاعات تکراریست، لطفا اطلاعات جدید وارد کنید",
            Toast.LENGTH_LONG
        ).show()
    }

}