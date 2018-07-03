package com.kubekbreha.instagrambot.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.ProgressActivity
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.util.StoreListAplicationClass
import kotlinx.android.synthetic.main.fragment_bottomsheet_actions.*
import org.jetbrains.anko.toast
import java.lang.Exception


class BottomNavigationDrawerFragmentActions : BottomSheetDialogFragment() {

    private var selectedList = -1
    private var openActivity = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_actions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_bottomsheet_navigation_view.setNavigationItemSelectedListener { menuItem ->

            try {
                selectedList = (context as StoreListAplicationClass).getData()!!
                (context as Activity).toast(selectedList)
                openActivity = true

            } catch (e: Exception) {
                (context as Activity).toast("You need to pick a list!")
            }


            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {

                R.id.bottom_menu_follow -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "follow")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
                    }
                }

                R.id.bottom_menu_comment -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "comment")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    }
                }

                R.id.bottom_menu_like -> {
                    if (openActivity) {
                        val intent = Intent(context, ProgressActivity::class.java)
                        intent.putExtra("listSelected", selectedList)
                        intent.putExtra("action", "like")
                        startActivity(intent)
                        (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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



