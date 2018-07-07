package com.kubekbreha.instagrambot.mainActivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.loginActivity.LoginActivity
import com.kubekbreha.instagrambot.R
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult
import kotlinx.android.synthetic.main.fragment_bottomsheet_actions.*


class BottomNavigationDrawerFragmentAccountSettings: BottomSheetDialogFragment() {

    private val TAG = "BotNavDrawFrag"
    lateinit var tagFeedClass: InstagramFeedResult


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_acount_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_bottomsheet_navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {

                R.id.app_bar_account_settings -> {                }
                R.id.app_bar_account_logout -> logOut()


            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }


    fun logOut(){
        val editor = context!!.getSharedPreferences("instagrambot_login", Context.MODE_PRIVATE).edit()
        editor.putString("username", "")
        editor.putString("password", "")
        editor.apply()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        (context as Activity).finish()
    }

}
