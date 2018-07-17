package com.kubekbreha.instagrambot.mainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kubekbreha.instagrambot.listActivity.AddListActivity
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.loginActivity.User
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        setSupportActionBar(activity_main_bottom_app_bar as Toolbar?)

        val newFragment = MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_frame, newFragment)
        transaction.commit()

        //get values from loginActivity
        val bundle = intent.extras
        if(intent.extras != null) {
            username = bundle.get("userName") as String
            password = bundle.get("userPassword") as String
            User.logIn(username, password, this)
        }


        activity_main_fab.setOnClickListener {
            val intent = Intent(this, AddListActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

            val editor = getSharedPreferences("instagrambot_listId", Context.MODE_PRIVATE).edit()
            editor.putInt("listId", -1)
            editor.apply()
        }

        activity_main_bottom_account_settings_menu.setOnClickListener{
            val bottomNavDrawerFragment = BottomNavigationDrawerFragmentAccountSettings()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
        }
        
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragmentActions()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }


}