package com.example.final_test_01.ui.main_activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.example.final_test_01.R
import com.example.final_test_01.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setBottomNavigation()
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