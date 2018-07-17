package com.kubekbreha.instagrambot.walkthroughActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kubekbreha.instagrambot.loginActivity.LoginActivity
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.walkthroughActivity.indicatorDots.WormDotsIndicator


class WalkthroughActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: WalkthroughSlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        val walkthroughted = prefs.getBoolean("done", false)

        if(walkthroughted){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        //hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.activity_walkthrough_wormDotsLayout)
        val viewPager = findViewById<ViewPager>(R.id.activity_walkthrough_viewPager)
        val adapter = WalkthroughSlideAdapter(this)
        viewPager.adapter = adapter
        wormDotsIndicator.setViewPager(viewPager)

    }

}

