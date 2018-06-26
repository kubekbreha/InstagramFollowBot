package com.kubekbreha.instagrambot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.niekirk.com.instagram4android.Instagram4Android
import dev.niekirk.com.instagram4android.requests.InstagramFollowRequest
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_bottom_app_bar)


        //get values from loginActivity
        val bundle = intent.extras
        username = bundle.get("userName") as String
        password = bundle.get("userPassword") as String

        //put shared preferences
        val editor = getSharedPreferences("instagrambot_login", Context.MODE_PRIVATE).edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()

        //start logging
        User.logIn(username, password, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.app_bar_settings ->  toast(User.getUser().isLoggedIn.toString())
            R.id.app_bar_logOut -> logOut()

            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }


    fun logOut(){
        val editor = getSharedPreferences("instagrambot_login", Context.MODE_PRIVATE).edit()
        editor.putString("username", "")
        editor.putString("password", "")
        editor.apply()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
