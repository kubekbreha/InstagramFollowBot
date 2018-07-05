package com.kubekbreha.instagrambot.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.User
import com.kubekbreha.instagrambot.UsersInList
import dev.niekirk.com.instagram4android.requests.InstagramGetUserFollowingRequest
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest
import kotlinx.android.synthetic.main.fragment_bottomsheet_add_user_to_list.*


class BottomNavigationDrawerFragmentAddUser : BottomSheetDialogFragment() {

    private var listId: Int = 0
    private var listName: String = ""
    private lateinit var userDat: UsersInList
    private val timeBetweenOneUser = 250


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listId = arguments!!.getInt("listId")
        listName = arguments!!.getString("listName")
        return inflater.inflate(R.layout.fragment_bottomsheet_add_user_to_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_drawer_add_list_button.isAllCaps = false
        bottom_drawer_add_usersFol_button.isAllCaps = false
        userDat = UsersInList(listId, context!!)

        bottom_drawer_add_usersFol_button.setOnClickListener {
            val user = bottom_drawer_usersFol_editText.text.toString()
            getUserFollowing(user)
            removeFragment(this)

        }

        bottom_drawer_add_list_button.setOnClickListener {
            userDat.addUser(bottom_drawer_list_editText.text.toString())
            removeFragment(this)
        }
    }


    private fun removeFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.remove(fragment)
        fragmentTransaction.commit()
    }


    private fun getUserFollowing(userName: String) {
        val thread = Thread(Runnable {
            try {
                val usersList = ArrayList<Long>()
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
                val instaUser = result.getUser()
                val userFollowers = User.getUser().sendRequest(InstagramGetUserFollowingRequest(instaUser.getPk()))
                val users = userFollowers.getUsers()
                for (userItem in users) {
                    usersList.add(userItem.getPk())
                }
                Log.e("MOTHERFUCK", instaUser.username + " follows " + instaUser.getFollowing_count() + " users.")


                val bigCircle = object : CountDownTimer((users.size * timeBetweenOneUser).toLong(), timeBetweenOneUser.toLong()) {
                    override fun onTick(millisUntilFinished: Long) {
                        for (userItem in users) {
                            userDat.addUser(userItem.username)
                        }
                    }

                    override fun onFinish() {

                    }
                }.start()


            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


}
