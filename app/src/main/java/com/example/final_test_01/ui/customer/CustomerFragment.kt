package com.example.final_test_01.ui.customer

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentCustomerBinding
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

    }

    private fun createCustomerOnClick() {
        with(binding) {
            btnSignUp.setOnClickListener {
                val firstName = etFirstNameCustomer.text.toString()
                val lastName = etLastNameCustomer.text.toString()
                val email = etEmailCustomer.text.toString()
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
                    Toast.makeText(requireContext(),"لطفا تمام اطلاعات رو کامل کنید",Toast.LENGTH_SHORT).show()
                } else{
                    viewModel?.createObjectDto(firstName, lastName, email)
                    Toast.makeText(requireContext(),"ثبت نام با موفقیت انجام شد",Toast.LENGTH_SHORT).show()
//                    viewModel?.customer?.observe(viewLifecycleOwner) {
//                        Log.e(TAG, "onViewCreated idi: ${it.email}")
//                    }
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

}