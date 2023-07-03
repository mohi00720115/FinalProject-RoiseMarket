package com.example.final_test_01.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun ImageView.showImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.snack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}