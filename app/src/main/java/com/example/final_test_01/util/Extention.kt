package com.example.final_test_01.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

fun ImageView.showImageByGlide(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.snack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun Double.formatPrice(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    return numberFormat.format(this)
}
