package com.kubekbreha.instagrambot.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.*
import dev.niekirk.com.instagram4android.requests.*
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary
import kotlinx.android.synthetic.main.fragment_bottomsheet_actions.*
import dev.niekirk.com.instagram4android.requests.InstagramUserFeedRequest




class BottomNavigationDrawerFragmentActions: BottomSheetDialogFragment() {

    private var selectedList = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_actions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_bottomsheet_navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {

                R.id.bottom_menu_follow -> {
                    val intent = Intent(context, ProgressActivity::class.java)
                    intent.putExtra("listSelected", selectedList)
                    intent.putExtra("action", "follow")
                    startActivity(intent)
                    (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//                    (context as Activity).finish()
                }

                R.id.bottom_menu_unfollow -> {
                    val intent = Intent(context, ProgressActivity::class.java)
                    intent.putExtra("listSelected", selectedList)
                    intent.putExtra("action", "unfollow")
                    startActivity(intent)
                    (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

                R.id.bottom_menu_comment -> {
                    val intent = Intent(context, ProgressActivity::class.java)
                    intent.putExtra("listSelected", selectedList)
                    intent.putExtra("action", "comment")
                    startActivity(intent)
                    (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

                R.id.bottom_menu_like -> {
                    val intent = Intent(context, ProgressActivity::class.java)
                    intent.putExtra("listSelected", selectedList)
                    intent.putExtra("action", "like")
                    startActivity(intent)
                    (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }

}



