package com.kubekbreha.instagramhelper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.WindowManager
import com.kubekbreha.instagrambot.loginActivity.User
import com.kubekbreha.instagramhelper.cards.CardAdapter
import com.kubekbreha.instagramhelper.cards.CardView
import com.kubekbreha.instagramhelper.cards.UsersListItem
import com.kubekbreha.instagramhelper.cards.UsersListItemsHandler


import com.kubekbreha.instagramhelper.discretescrollview.DiscreteScrollView
import com.kubekbreha.instagramhelper.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>,
        View.OnClickListener {

    private lateinit var username: String
    private lateinit var password: String

    private var lists: List<UsersListItem>? = null

    private var cardListVIew: CardView? = null
    private var listPicker: DiscreteScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set fullscreen activity
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        //settings button
        activity_main_settings_button.setOnClickListener(this)

        //get values from loginActivity
        val bundle = intent.extras
        if(intent.extras != null) {
            username = bundle.get("userName") as String
            password = bundle.get("userPassword") as String
            User.logIn(username, password, this)
        }

        lists = UsersListItemsHandler.get().lists
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
        activity_main_user_name.text = User.getUser().username
        activity_main_user_name.text = User.getUser().username
    }

    override fun onClick(p0: View?) {
        when(p0) {
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
}
