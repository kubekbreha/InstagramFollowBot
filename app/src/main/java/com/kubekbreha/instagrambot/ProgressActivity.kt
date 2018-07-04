package com.kubekbreha.instagrambot

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kubekbreha.instagrambot.util.ArcProgressStackView
import com.kubekbreha.instagrambot.util.Model
import dev.niekirk.com.instagram4android.requests.*
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary
import org.jetbrains.anko.toast
import java.lang.Thread.sleep
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger
import android.os.CountDownTimer



class ProgressActivity : AppCompatActivity() {

    private val TAG = "BotNavDrawFrag"
    lateinit var tagFeedClass: InstagramFeedResult

    private var selectedListId: Int = -1
    private var action: String = ""

    private var mArcProgressStackView: ArcProgressStackView? = null
    val models = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)


        mArcProgressStackView = findViewById(R.id.activity_progress_arcProgressStackView)

        // Set models
        models.add(Model("WholeList", 0, Color.parseColor("#6D8DFA"), R.color.colorAccent))
        models.add(Model("OneItem", 0, Color.parseColor("#6052E5"), R.color.colorAccent))
        mArcProgressStackView!!.models = models


        val bundle = intent.extras
        if (bundle != null) {
            selectedListId = bundle.get("listSelected") as Int
            action = bundle.get("action") as String
        }

        when (action) {
            "follow" -> {
                followListOfUsers(selectedListId)
            }

            "unfollow" -> {
                unFollowListOfUsers(selectedListId)
            }

            "comment" -> {

            }

            "like" -> {

            }
        }
    }


    private fun followListOfUsers(selectedList: Int) {
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()

        val fullSize = namesOfUsers.size
        val percentage = (1f / fullSize) * 99
        var addValue = percentage


        toast(fullSize.toString() + " " + percentage.toString())

        var user = 0
        val cdt = object : CountDownTimer(fullSize*3000L, 3000) {

            override fun onTick(millisUntilFinished: Long) {
                follow(namesOfUsers[user])
                models[0].progress = addValue
                models[1].progress = addValue
                addValue += percentage
                mArcProgressStackView!!.animateProgress()
                user++
            }

            override fun onFinish() {
                models[0].progress = 100.0f
                models[1].progress = 100.0f
                mArcProgressStackView!!.animateProgress()
            }
        }.start()
    }


    private fun follow(user: String) {
        toast(user)

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


    private fun unFollowListOfUsers(selectedList: Int) {
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()
        for (user in namesOfUsers) {
            toast(user)
            unFollow(user)
            //[JB 1.7.2018]TODO add break for some time here
        }
    }



    private fun unFollow(user: String) {
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


    private fun like(pk: Long) {
        val thread = Thread(Runnable {
            try {
                User.getUser().sendRequest(InstagramLikeRequest(pk))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    private fun getPostIDs(userName: String) {
        var tagFeed: InstagramFeedResult?
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


    private fun getUserFollowers(userResult: InstagramSearchUsernameResult): MutableList<InstagramUserSummary>? {
        val githubFollowers = User.getUser().sendRequest(InstagramGetUserFollowersRequest(userResult.user.getPk()))
        val users = githubFollowers.getUsers()
        for (user in users) {
            println("User " + user.getUsername() + " follows " + userResult.user.username)
        }
        return users
    }


    private fun searchUser(userName: String): InstagramSearchUsernameResult {
        val userResult = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
        Log.i(TAG, "ID for @" + userResult.user.username + " " + userResult.user.getPk())
        Log.i(TAG, "Number of followers: " + userResult.user.getFollower_count())
        return userResult
    }

}
