package com.kubekbreha.instagrambot.walkthrough

import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.UsersList

class WalkthroughActivity : AppCompatActivity() {

    private lateinit var slideViewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout

    private var dots: MutableList<TextView> = mutableListOf()

    private lateinit var sliderAdapter: WalkthroughSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        slideViewPager = findViewById(R.id.activity_walkthrough_viewPager)
        dotsLayout = findViewById(R.id.activity_walkthrough_dotsLayout)

        sliderAdapter = WalkthroughSlideAdapter(this)

        slideViewPager.adapter = sliderAdapter

        addDotsIndicator()
    }


    private fun addDotsIndicator(){

        for (item in 0..3) {
            dots.add(item, TextView(this))
        }

        for (item in 1..3) {
            dots[item].text = Html.fromHtml("&#8226;")
            dots[item].textSize = 35F
            dots[item].setTextColor(resources.getColor(R.color.white))

            dotsLayout.addView(dots[item])
        }

    }
}
