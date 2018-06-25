package com.kubekbreha.instagrambot

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.niekirk.com.instagram4android.Instagram4Android
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.progressDialog


class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(bottom_app_bar)


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
            } finally {
                dialog.dismiss()
            }
        })
        thread.start()
//        button.setOnClickListener {
//            toast(instagramUser.isLoggedIn.toString())
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.app_bar_fav -> toast(getString(R.string.fav_clicked))
            R.id.app_bar_search -> toast(getString(R.string.search_clicked))
            R.id.app_bar_settings -> toast(getString(R.string.settings_clicked))
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

    // This is an extension method for easy Toast call
    fun Context.toast(message: CharSequence) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 325)
        toast.show()
    }

}
