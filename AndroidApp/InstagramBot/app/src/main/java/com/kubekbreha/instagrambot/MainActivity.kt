package com.kubekbreha.instagrambot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dev.niekirk.com.instagram4android.Instagram4Android
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get values from loginActivity
        val bundle = intent.extras
        username = bundle.get("userName") as String
        password = bundle.get("userPassword") as String

        //start logging
        val dialog = progressDialog(message = "Please wait a bit ...", title = "Logging in ...")
        dialog.show()

        val instagramUser = Instagram4Android.builder().username(username).password(password).build()
        val thread = Thread(Runnable {
            try {
                instagramUser.setup()
                instagramUser.login()
            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                dialog.dismiss()
            }
        })
        thread.start()


        button.setOnClickListener{
            toast(instagramUser.isLoggedIn.toString())
        }

    }





}
