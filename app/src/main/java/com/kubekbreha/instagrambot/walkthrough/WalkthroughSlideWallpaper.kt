package com.kubekbreha.instagrambot.walkthrough

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.kubekbreha.instagrambot.R
import androidx.viewpager.widget.ViewPager
import android.widget.TextView



class WalkthroughSlideWallpaper(context: Context) : PagerAdapter() {

    private var context = context
    private lateinit var layoutInflater: LayoutInflater


    val slideImages: IntArray = intArrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    )


    val slideTexts = arrayOf(
            "TESTICEK1",
            "TESTICEK2",
            "TESTICEK3"
    )


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun getCount(): Int {
        return slideImages.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val viewPagerItem = LayoutInflater.from(container.context).inflate(R.layout.walkthrough_slide_layout, container, false)

        val text = viewPagerItem.findViewById<TextView>(R.id.walkthrough_slide_layout_text)
        
        text.text = slideTexts[position]

        container.addView(viewPagerItem)
        return viewPagerItem
    }

}