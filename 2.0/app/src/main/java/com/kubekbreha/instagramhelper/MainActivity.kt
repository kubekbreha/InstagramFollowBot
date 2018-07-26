package com.kubekbreha.instagramhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.kubekbreha.instagrambot.loginActivity.User
import com.kubekbreha.instagramhelper.cards.CardAdapter
import com.kubekbreha.instagramhelper.cards.CardView
import com.kubekbreha.instagramhelper.cards.UsersListItem
import com.kubekbreha.instagramhelper.cards.UsersListItemsHandler


import com.kubekbreha.instagramhelper.discretescrollview.DiscreteScrollView
import com.kubekbreha.instagramhelper.discretescrollview.transform.ScaleTransformer
import dev.niekirk.com.instagram4android.requests.InstagramGetUserFollowingRequest
import dev.niekirk.com.instagram4android.requests.InstagramSearchUsernameRequest
import dev.niekirk.com.instagram4android.requests.payload.InstagramUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>,
        View.OnClickListener {

    private lateinit var username: String
    private lateinit var password: String

    private lateinit var followersCount: TextView
    private lateinit var followingCount: TextView

    private var lists: List<UsersListItem>? = null

    private var cardListVIew: CardView? = null
    private var listPicker: DiscreteScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set fullscreen activity
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        followersCount = findViewById(R.id.activity_main_user_followers_count)
        followingCount = findViewById(R.id.activity_main_user_following_count)


        //settings button
        activity_main_settings_button.setOnClickListener(this)


        lists = UsersListItemsHandler("test").getCardsList()
        listPicker = findViewById(R.id.activity_main_discreteScrollView)
        listPicker!!.setSlideOnFling(true)
        listPicker!!.adapter = CardAdapter(lists!!)
        listPicker!!.addOnItemChangedListener(this)
        listPicker!!.addScrollStateChangeListener(this)
        listPicker!!.scrollToPosition(0)
        listPicker!!.setItemTransitionTimeMillis(300)
        listPicker!!.setItemTransformer(ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        cardListVIew = findViewById(R.id.activity_main_cardView)
        cardListVIew!!.setGradientView(lists!![0])

    }

    override fun onResume() {
        super.onResume()
        activity_main_user_name.text = User.getUser().username
        getFollowingCout()
        getFollowersCout()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            activity_main_settings_button -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    override fun onCurrentItemChanged(holder: RecyclerView.ViewHolder?, position: Int) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
            cardListVIew!!.setGradientView(lists!![position])

        }
    }

    override fun onScrollStart(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onScroll(position: Float, currentIndex: Int, newIndex: Int, currentHolder: RecyclerView.ViewHolder?, newHolder: RecyclerView.ViewHolder?) {
        val current = lists!![currentIndex]
        if (newIndex >= 0 && newIndex < listPicker!!.adapter.itemCount) {
            val next = lists!![newIndex]
            cardListVIew!!.onScroll(1f - Math.abs(position), current, next)
        }
    }


    override fun onScrollEnd(holder: RecyclerView.ViewHolder, position: Int) {

    }


    private fun getFollowersCout(){
        var instaUser: InstagramUser? = null
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(User.instagramUser.username))
                instaUser = result.getUser()
                followersCount.text = instaUser!!.getFollower_count().toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }


    private fun getFollowingCout() {
        var instaUser: InstagramUser? = null
        val thread = Thread(Runnable {
            try {
                val result = User.getUser().sendRequest(InstagramSearchUsernameRequest(User.instagramUser.username))
                instaUser = result.getUser()
                followingCount.text = instaUser!!.getFollowing_count().toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }
}
