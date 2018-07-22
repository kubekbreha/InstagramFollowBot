package com.kubekbreha.instagramhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.graphics.drawable.AnimationDrawable
import android.support.constraint.ConstraintLayout
import kotlinx.android.synthetic.main.activity_login.*


class AddNewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new)

        //hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)


        val animationDrawable = activity_login_root_layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

    }


}
