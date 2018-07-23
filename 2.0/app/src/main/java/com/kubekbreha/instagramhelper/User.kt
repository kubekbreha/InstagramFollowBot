package com.kubekbreha.instagrambot.loginActivity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kubekbreha.instagramhelper.LoginActivity
import com.kubekbreha.instagramhelper.MainActivity
import dev.niekirk.com.instagram4android.Instagram4Android
import org.jetbrains.anko.toast

class User {
    companion object {

        lateinit var instagramUser: Instagram4Android

        fun logIn(username: String, password: String, context: Context) {

            val progress = ProgressDialog(context)
            progress.setMessage("Logging in ...")
            progress.show()
            instagramUser = Instagram4Android.builder().username(username).password(password).build()
            val thread = Thread(Runnable {
                try {
                    instagramUser.setup()
                    instagramUser.login()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    progress.dismiss()
                    if (!getUser().isLoggedIn) {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        (context).finish()
                    } else {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        (context).finish()
                    }
                }
            })
            thread.start()
        }


        fun getUser(): Instagram4Android {
            return instagramUser
        }

    }
}