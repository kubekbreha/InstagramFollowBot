package com.kubekbreha.instagramhelper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val prefs = getSharedPreferences("instagrambot_login", Context.MODE_PRIVATE)
        username = prefs.getString("username", "")
        password = prefs.getString("password", "")

        if (username != "" && password != "") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", username)
            intent.putExtra("userPassword", password)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        activity_login_button_logIn.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.activity_login_button_logIn -> {
                username = activity_login_editText_username.text.toString()
                password = activity_login_editText_password.text.toString()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userName", username)
                intent.putExtra("userPassword", password)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }



}

