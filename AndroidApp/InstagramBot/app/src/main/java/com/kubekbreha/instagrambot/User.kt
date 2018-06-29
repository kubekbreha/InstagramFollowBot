package com.kubekbreha.instagrambot

import android.content.Context
import com.kubekbreha.instagrambot.util.Progress
import dev.niekirk.com.instagram4android.Instagram4Android


class User {
    companion object {


        lateinit var instagramUser: Instagram4Android


        fun logIn(username: String, password: String, context: Context) {

            val progress = Progress(context)
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
                }
            })
            thread.start()
        }



        fun getUser(): Instagram4Android {
            return instagramUser
        }


    }
}