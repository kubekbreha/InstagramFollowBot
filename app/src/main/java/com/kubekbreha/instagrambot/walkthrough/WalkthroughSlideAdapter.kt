package com.kubekbreha.instagrambot.walkthrough

import android.app.Activity
import android.app.AppComponentFactory
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.kubekbreha.instagrambot.R
import androidx.viewpager.widget.ViewPager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startActivity
import com.kubekbreha.instagrambot.LoginActivity
import com.kubekbreha.instagrambot.MainActivity


class WalkthroughSlideAdapter(context: Context) : PagerAdapter() {

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

        val loginButton = viewPagerItem.findViewById<AppCompatButton>(R.id.activity_walkthrough_loginButton)

        if (position == 2) {
            loginButton.visibility = View.VISIBLE
            loginButton.setOnClickListener {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                (context as Activity).finish()
            }
        }

        return viewPagerItem
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}