package com.example.final_test_01.ui.activity.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.example.final_test_01.R
import com.example.final_test_01.databinding.ActivityMainBinding
import com.example.final_test_01.ui.activity.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUi()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun setUi() {
        setBottomNavigation()
        intentIntoSearchActivity()
        changeStatusBarColor()
    }

    /**
     * رفتن به اکتیویتی سرچ
     */
    private fun intentIntoSearchActivity() {
        binding.searchView.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Create BottomNavigation
     */
    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.containerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNav
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    /**
     * تغییر رنگ، نوار وضعیت بالا و پایین گوشی موبایل
     */
    @RequiresApi(Build.VERSION_CODES.P)
    private fun changeStatusBarColor() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
    }

    /**
     * فانکشنی برای نمایش دیالوگ متریال
     */
    private fun previewMaterialDialog() {
        MaterialDialog(this).show {
            title(text = "عنوان")
            message(text = "پیام")
            cancelOnTouchOutside(false)
            icon(R.drawable.ic_launcher_foreground)
            positiveButton(text = "تأیید") { dialog ->
                Log.d(TAG, "onCreate: Dadashami :)")
            }
            negativeButton(text = "لغو")
        }
    }
}