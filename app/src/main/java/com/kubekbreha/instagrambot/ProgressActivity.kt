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
import java.util.ArrayList

class ProgressActivity : AppCompatActivity() {

    private val TAG = "BotNavDrawFrag"
    lateinit var tagFeedClass: InstagramFeedResult

    private var selectedListId : Int = -1
    private var action: String = ""

    private var mArcProgressStackView: ArcProgressStackView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)


        mArcProgressStackView = findViewById(R.id.apsv) as ArcProgressStackView

        // Set models
        val models = ArrayList<Model>()
        models.add(Model("Strategy", 0, Color.parseColor("#ff34ff"), R.color.colorAccent))
        models.add(Model("Design", 0, Color.parseColor("#ff98ff"), R.color.colorAccentDark))
        mArcProgressStackView!!.setModels(models)




        val bundle = intent.extras
        if (bundle != null) {
            selectedListId = bundle.get("listSelected") as Int
            action = bundle.get("action") as String
        }

        toast(selectedListId.toString() + " " + action).show()


        when(action){
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



    private fun followListOfUsers(selectedList: Int){
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()
        for (user in namesOfUsers){
            toast(user)
            follow(user)
            //[JB 1.7.2018]TODO add break for some time here
        }
    }



    private fun unFollowListOfUsers(selectedList: Int){
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()
        for (user in namesOfUsers){
            toast(user)
            unFollow(user)
            //[JB 1.7.2018]TODO add break for some time here
        }
    }


    private fun follow(user: String) {
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


    private fun getPostIDs(userName: String){
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


    private fun getUserFollowers(userResult : InstagramSearchUsernameResult): MutableList<InstagramUserSummary>? {
        val githubFollowers = User.getUser().sendRequest(InstagramGetUserFollowersRequest(userResult.user.getPk()))
        val users = githubFollowers.getUsers()
        for (user in users) {
            println("User " + user.getUsername() + " follows " + userResult.user.username)
        }
        return users
    }


    private fun searchUser(userName: String): InstagramSearchUsernameResult {
        val userResult = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
        Log.i(TAG,"ID for @"+ userResult.user.username +" " + userResult.user.getPk())
        Log.i(TAG, "Number of followers: " + userResult.user.getFollower_count())
        return userResult
    }

}
