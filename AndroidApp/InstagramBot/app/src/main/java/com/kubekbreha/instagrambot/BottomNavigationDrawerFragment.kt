package com.kubekbreha.instagrambot

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.fragments.CommentFragment
import com.kubekbreha.instagrambot.fragments.FollowFragment
import com.kubekbreha.instagrambot.fragments.LikeFragment
import dev.niekirk.com.instagram4android.requests.*
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary
import kotlinx.android.synthetic.main.fragment_bottomsheet.*
import dev.niekirk.com.instagram4android.requests.InstagramUserFeedRequest




class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    private val TAG = "BotNavDrawFrag"
    lateinit var tagFeedClass: InstagramFeedResult


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_bottomsheet_navigation_view.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {
//                R.id.bottom_menu_follow -> follow("therock")
//                R.id.bottom_menu_unfollow -> unFollow("therock")
//                R.id.bottom_menu_comment -> getPostIDs("therock")
//                R.id.bottom_menu_like -> like(tagFeedClass.items[0].pk)


                R.id.bottom_menu_follow -> {
                    val followFragment = FollowFragment.newInstance()
                    openFragment(followFragment)
                }
                R.id.bottom_menu_comment -> {
                    val commentFragment = CommentFragment.newInstance()
                    openFragment(commentFragment)
                }
                R.id.bottom_menu_like -> {
                    val likeFragment = LikeFragment.newInstance()
                    openFragment(likeFragment)
                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }
    }




    override fun onStart() {
        super.onStart()
        val songsFragment = FollowFragment.newInstance()
        openFragment(songsFragment)
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.activity_main_frame, fragment)

        //transaction.addToBackStack(null)
        transaction.commit()
    }





    fun follow(user: String){
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


    fun unFollow(user: String){
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



    fun like(pk: Long) {
        val thread = Thread(Runnable {
            try {
                User.getUser().sendRequest(InstagramLikeRequest(pk))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    private fun getPostIDs(userName: String){
        var tagFeed: InstagramFeedResult? = null
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
                tagFeed = User.getUser().sendRequest(InstagramUserFeedRequest(result.user.pk, 5.toString(), 50))
                for (feedResult in tagFeed!!.items) {
                    Log.e("POSTDEBUG", "Post ID: " + feedResult.getPk())
                }
                tagFeedClass = tagFeed!!
                Log.e("POSTDEBUG", "Finished")


            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    fun getUserFollowers(userResult : InstagramSearchUsernameResult): MutableList<InstagramUserSummary>? {
        val githubFollowers = User.getUser().sendRequest(InstagramGetUserFollowersRequest(userResult.user.getPk()))
        val users = githubFollowers.getUsers()
        for (user in users) {
            println("User " + user.getUsername() + " follows " + userResult.user.username)
        }
        return users
    }


    fun searchUser(userName: String): InstagramSearchUsernameResult {
        val userResult = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
        Log.i(TAG,"ID for @"+ userResult.user.username +" " + userResult.user.getPk())
        Log.i(TAG, "Number of followers: " + userResult.user.getFollower_count())
        return userResult
    }

}