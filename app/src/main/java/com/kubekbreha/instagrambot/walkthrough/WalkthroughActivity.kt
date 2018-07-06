package com.kubekbreha.instagrambot.walkthrough

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.R

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
    }

}
