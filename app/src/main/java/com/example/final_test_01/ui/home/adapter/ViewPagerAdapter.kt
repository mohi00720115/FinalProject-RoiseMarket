package com.example.final_test_01.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.final_test_01.databinding.SliderItemBinding
import com.example.final_test_01.util.showImage

class ViewPagerAdapter(val context: Context, val imageList: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater = LayoutInflater.from(context)
        val itemView: SliderItemBinding =
            SliderItemBinding.inflate(mLayoutInflater, container, false)
        itemView.imageViewSlider.showImage(imageList[position])
        container.addView(itemView.root)
        return itemView.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
