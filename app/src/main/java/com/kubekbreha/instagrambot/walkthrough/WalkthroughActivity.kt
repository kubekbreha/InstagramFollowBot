package com.kubekbreha.instagrambot.walkthrough

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.LoginActivity
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.indicatorDots.SpringDotsIndicator
import com.kubekbreha.instagrambot.indicatorDots.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_walkthrough.*


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


        activity_walkthrough_loginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }

}
