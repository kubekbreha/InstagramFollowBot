package com.kubekbreha.instagrambot

import dev.niekirk.com.instagram4android.Instagram4Android

class User{

    lateinit var instagramUser: Instagram4Android


    fun logIn(username: String, password:String){
        instagramUser = Instagram4Android.builder().username(username).password(password).build()
        val thread = Thread(Runnable {
            try {
                instagramUser.setup()
                instagramUser.login()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    fun getUser(): Instagram4Android {
        return instagramUser
    }




}