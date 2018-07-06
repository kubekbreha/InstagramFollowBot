package com.kubekbreha.instagrambot.walkthrough

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.indicatorDots.WormDotsIndicator


class WalkthroughActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: WalkthroughSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.activity_walkthrough_wormDotsLayout)
        val viewPager = findViewById<ViewPager>(R.id.activity_walkthrough_viewPager)
        val adapter = WalkthroughSlideAdapter(this)
        viewPager.adapter = adapter
        wormDotsIndicator.setViewPager(viewPager)

    }

}

