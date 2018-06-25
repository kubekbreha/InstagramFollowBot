package com.kubekbreha.instagrambot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        activity_login_button_logIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.activity_login_button_logIn -> {

                username = activity_login_editText_username.text.toString()
                password = activity_login_editText_password.text.toString()
                toast(username + " " + password)

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userName", username)
                intent.putExtra("userPassword", password)
                startActivity(intent)

            }
        }
    }

}

