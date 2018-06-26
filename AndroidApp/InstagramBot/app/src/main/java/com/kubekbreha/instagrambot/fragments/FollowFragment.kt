package com.kubekbreha.instagrambot.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.User
import dev.niekirk.com.instagram4android.requests.InstagramFollowRequest
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest
import dev.niekirk.com.instagram4android.requests.InstagramUnfollowRequest


class FollowFragment : Fragment(), View.OnClickListener{
    private lateinit var followButton :Button
    private lateinit var unFollowButton :Button

    companion object {
        fun newInstance(): FollowFragment = FollowFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_follow,
                container, false)

        followButton = view.findViewById(R.id.fragment_follow_folButton)
        unFollowButton = view.findViewById(R.id.fragment_follow_unfolButton)

        followButton.setOnClickListener(this)
        unFollowButton.setOnClickListener(this)

        return view
    }



    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.fragment_follow_folButton -> {
                follow("therock")
            }
            R.id.fragment_follow_unfolButton -> {
                unFollow("therock")
            }
        }
    }


    fun follow(user: String) {
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(user))
                val user = result.user
                User.getUser().sendRequest(InstagramFollowRequest(user.getPk()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    fun unFollow(user: String) {
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(user))
                val user = result.user
                User.getUser().sendRequest(InstagramUnfollowRequest(user.getPk()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }
}
