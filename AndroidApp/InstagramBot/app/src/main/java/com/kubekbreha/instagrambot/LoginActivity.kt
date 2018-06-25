package com.kubekbreha.instagrambot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.niekirk.com.instagram4android.Instagram4Android
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var username: String
    private lateinit var password: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        activity_login_button_logIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.activity_login_button_logIn -> {
                val dialog = progressDialog(message = "Please wait a bit ...", title = "Logging in ...")

                dialog.show()
                username = activity_login_editText_username.text.toString()
                password = activity_login_editText_username.text.toString()

                val instagramUser = Instagram4Android.builder().username(username).password(password).build()

                val thread = Thread(Runnable {
                    try {

                        instagramUser.setup()
                        instagramUser.login()


                        if (instagramUser.isLoggedIn){
//                            val intentInstagramUser = InstagramUser(instagramUser)

                            val intent = Intent(this, MainActivity::class.java)
//                            intent.putExtra("instagramUser", intentInstagramUser)
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }finally {
                        dialog.dismiss()
                    }
                })


                thread.start()


            }
        }
    }
}
