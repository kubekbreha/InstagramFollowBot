package com.kubekbreha.instagrambot.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.ProgressActivity
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.fragment_bottomsheet_actions.*
import org.jetbrains.anko.toast


class BottomNavigationDrawerFragmentActions : BottomSheetDialogFragment() {

    private var selectedList = -1
    private var openActivity = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_actions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_bottomsheet_navigation_view.setNavigationItemSelectedListener { menuItem ->

            val prefs = context!!.getSharedPreferences("instagrambot_listId", Context.MODE_PRIVATE)
            selectedList = prefs.getInt("listId", -1)

            if (selectedList == -1) {
                (context as Activity).toast("You need to pick a list!")
            }else{
                openActivity = true
            }

            val editor = context!!.getSharedPreferences("instagrambot_listId", Context.MODE_PRIVATE).edit()
            editor.putInt("listId", -1)
            editor.apply()


            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {

                R.id.bottom_menu_follow -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "follow")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        removeFragment(this)
//                    (context as Activity).finish()
                    }
                }

                R.id.bottom_menu_unfollow -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "unfollow")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        removeFragment(this)
                    }
                }

                R.id.bottom_menu_comment -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "comment")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        removeFragment(this)
                    }
                }

                R.id.bottom_menu_like -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "like")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        removeFragment(this)
                    }
                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }

    private fun removeFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }

}



