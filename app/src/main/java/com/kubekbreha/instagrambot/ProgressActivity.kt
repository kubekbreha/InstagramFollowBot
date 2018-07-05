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
import kotlin.math.min


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
                followListOfUsers(selectedListId, true)
            }

            "unfollow" -> {
                followListOfUsers(selectedListId, false)
            }

            "comment" -> {

            }

            "like" -> {

            }
        }
    }


    private fun followListOfUsers(selectedList: Int, follow: Boolean) {
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()

        val fullSize = namesOfUsers.size
        val percentage = (1f / fullSize) * 99
        var addValue = 0f

        var user = 0
        val bigCircle = object : CountDownTimer(fullSize*7000L, 7000) {
            override fun onTick(millisUntilFinished: Long) {
                if (follow) {
                    follow(namesOfUsers[user], true)
                }else{
                    follow(namesOfUsers[user], false)
                }
                mArcProgressStackView!!.models[0].progress = addValue
                mArcProgressStackView!!.animateProgress()

                //one user circle
                val miniValue = ((500f/(7000-500))*100)
                var addValueSmall = 0f
                val smallCircle = object : CountDownTimer(7000, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                        mArcProgressStackView!!.models[0].progress = addValue
                        mArcProgressStackView!!.models[1].progress = addValueSmall
                        mArcProgressStackView!!.animateProgress()
                        addValueSmall += miniValue
                    }

                    override fun onFinish() {
                        addValueSmall = 0f
                    }
                }.start()

                //add value to big circle
                addValue += percentage
                user++
            }

            override fun onFinish() {
                models[0].progress = 100.0f
                models[1].progress = 100.0f
                mArcProgressStackView!!.animateProgress()
            }
        }.start()
    }


    private fun follow(user: String, follow: Boolean) {
        toast(user)

        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(user))
                val user = result.user
                if (follow) {
                    User.getUser().sendRequest(InstagramFollowRequest(user.getPk()))
                }else{
                    User.getUser().sendRequest(InstagramUnfollowRequest(user.getPk()))
                }
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
