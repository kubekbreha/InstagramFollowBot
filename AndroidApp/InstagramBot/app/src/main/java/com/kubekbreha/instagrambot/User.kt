package com.kubekbreha.instagrambot

import android.content.Context
import android.view.View
import dev.niekirk.com.instagram4android.Instagram4Android
import org.jetbrains.anko.progressDialog

class User {
    companion object {


        lateinit var instagramUser: Instagram4Android


        fun logIn(username: String, password: String, context: Context) {
            val dialog = context.progressDialog(message = "Please wait a bit ...", title = "Logging in ...")
            dialog.show()
            instagramUser = Instagram4Android.builder().username(username).password(password).build()
            val thread = Thread(Runnable {
                try {
                    instagramUser.setup()
                    instagramUser.login()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    dialog.dismiss()
                }
            })
            thread.start()
        }



        fun getUser(): Instagram4Android {
            return instagramUser
        }


    }
}