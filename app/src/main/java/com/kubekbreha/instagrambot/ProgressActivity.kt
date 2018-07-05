package com.kubekbreha.instagrambot

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kubekbreha.instagrambot.util.ArcProgressStackView
import com.kubekbreha.instagrambot.util.Model
import dev.niekirk.com.instagram4android.requests.*
import dev.niekirk.com.instagram4android.requests.payload.InstagramComment
import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsernameResult
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary
import kotlinx.android.synthetic.main.activity_progress.*
import java.util.*
import dev.niekirk.com.instagram4android.requests.InstagramTagFeedRequest


class ProgressActivity : AppCompatActivity() {

    private val TAG = "BotNavDrawFrag"
    lateinit var tagFeedClass: InstagramFeedResult

    private var selectedListId: Int = -1
    private var action: String = ""

    private var mArcProgressStackView: ArcProgressStackView? = null
    val models = ArrayList<Model>()

    private val pauseBetweenUsers = 7000
    private val pauseBetweenOneUser = 500f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        mArcProgressStackView = findViewById(R.id.activity_progress_arcProgressStackView)

        // Set models
        models.add(Model("WholeList", 0, Color.parseColor("#6D8DFA"), R.color.colorAccent))
        models.add(Model("OneUser", 0, Color.parseColor("#6052E5"), R.color.colorAccent))
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
                likeListOfUsersPosts(selectedListId)
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun followListOfUsers(selectedList: Int, follow: Boolean) {
        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()

        val fullSize = namesOfUsers.size.toLong()
        val percentage = (1f / fullSize) * 99
        var addValue = 0f

        var user = 0
        val bigCircle = object : CountDownTimer(fullSize * pauseBetweenUsers, pauseBetweenUsers.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                if (follow) {
                    follow(namesOfUsers[user], true)
                } else {
                    follow(namesOfUsers[user], false)
                }
                mArcProgressStackView!!.models[0].progress = addValue
                mArcProgressStackView!!.animateProgress()

                //one user circle
                val miniValue = ((pauseBetweenOneUser / (pauseBetweenUsers - pauseBetweenOneUser)) * 100)
                var addValueSmall = 0f
                val smallCircle = object : CountDownTimer(pauseBetweenUsers.toLong(), pauseBetweenOneUser.toLong()) {
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


    private fun follow(userName: String, follow: Boolean) {
        if (follow) {
            activity_progress_currentlyInAction_textView.text = "Following: " + userName
        } else {
            activity_progress_currentlyInAction_textView.text = "Unfollowing: " + userName
        }
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
                val user = result.user
                if (follow) {
                    User.getUser().sendRequest(InstagramFollowRequest(user.getPk()))
                } else {
                    User.getUser().sendRequest(InstagramUnfollowRequest(user.getPk()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    private fun likeListOfUsersPosts(selectedList: Int) {

        val users = UsersInList(selectedList, this)
        val namesOfUsers = users.getUsers()

        val fullSize = namesOfUsers.size.toLong()
        val percentage = (1f / fullSize) * 99
        var addValue = 0f

        var user = 0
        val bigCircle = object : CountDownTimer(fullSize * pauseBetweenUsers, pauseBetweenUsers.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                likePostsByUser(namesOfUsers[user])
                mArcProgressStackView!!.models[0].progress = addValue
                mArcProgressStackView!!.animateProgress()

                //one user circle
                val miniValue = ((pauseBetweenOneUser / (pauseBetweenUsers - pauseBetweenOneUser)) * 100)
                var addValueSmall = 0f
                val smallCircle = object : CountDownTimer(pauseBetweenUsers.toLong(), pauseBetweenOneUser.toLong()) {
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


    private fun likePostsByUser(userName: String) {
        activity_progress_currentlyInAction_textView.text = "Liking posts of: " + userName

        var tagFeed: InstagramFeedResult?

        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(userName))
                Log.e("preDEBUG", (result.user.pk).toString())

                tagFeed = User.getUser().sendRequest(InstagramUserFeedRequest(result.user.pk, 5.toString(), 50))

                for (feedResult in tagFeed!!.items) {
                    Log.e("POSTDEBUG", "Post ID: " + feedResult.getPk())
                    User.getUser().sendRequest(InstagramLikeRequest(feedResult.getPk()))
                }

                tagFeedClass = tagFeed!!
                Log.e("POSTDEBUG", "Finished")


            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    private fun likePostsByTag(tag: String) {
        activity_progress_currentlyInAction_textView.text = "Liking posts of tag: " + tag

        var tagFeed: InstagramFeedResult?

        val thread = Thread(Runnable {
            try {
                tagFeed = User.getUser().sendRequest(InstagramTagFeedRequest(tag, "10"))
                Log.e("POSTDEBUG", tag + " Post ID: " + tagFeed!!.items.size)

                for (feedResult in tagFeed!!.items) {
                    Log.e("POSTDEBUG", "Post ID: " + feedResult.getPk())
                    User.getUser().sendRequest(InstagramLikeRequest(feedResult.getPk()))
                }

                tagFeedClass = tagFeed!!
                Log.e("POSTDEBUG", "Finished")


            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


}
