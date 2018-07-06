package com.kubekbreha.instagrambot.walkthrough

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.LoginActivity
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.activity_walkthrough.*


class WalkthroughActivity : AppCompatActivity() {

    private lateinit var slideViewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout


    private lateinit var sliderAdapter: WalkthroughSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)


        slideViewPager = findViewById(R.id.activity_walkthrough_viewPager)
        dotsLayout = findViewById(R.id.activity_walkthrough_dotsLayout)
        sliderAdapter = WalkthroughSlideAdapter(this)
        slideViewPager.adapter = sliderAdapter

        addDotsIndicator(0)
//        slideViewPager.addOnPageChangeListener(object : OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {}
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//            override fun onPageSelected(position: Int) {
//                addDotsIndicator(position)
//            }
//        })


        activity_walkthrough_loginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }

    private fun addDotsIndicator(position: Int) {


        val dots = arrayOfNulls<TextView>(3)

        if(dotsLayout.getChildAt(0)!=null)
            dotsLayout.removeViewAt(0)


        if(dotsLayout.getChildAt(1)!=null)
            dotsLayout.removeViewAt(1)

        if(dotsLayout.getChildAt(2)!=null)
            dotsLayout.removeViewAt(2)


        for (i in 0 until 3) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35F

            dotsLayout.addView(dots[i], i)
        }

        dots[position]!!.setTextColor(resources.getColor(R.color.colorAccent))
    }


}
