package com.kubekbreha.instagramhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.kubekbreha.instagrambot.loginActivity.User
import org.jetbrains.anko.toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //set fullscreen activity
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (User.getUser().isLoggedIn) {
            toast(User.getAccessToken().toString()).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
