package com.kubekbreha.instagramhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager


import com.kubekbreha.instagramhelper.discretescrollview.DiscreteScrollView
import com.kubekbreha.instagramhelper.discretescrollview.transform.ScaleTransformer


class MainActivity : AppCompatActivity(), DiscreteScrollView.ScrollStateChangeListener<CardAdapter.ViewHolder>, DiscreteScrollView.OnItemChangedListener<CardAdapter.ViewHolder> {

    private var lists: List<ListItem>? = null

    private var cardListVIew: CardView? = null
    private var listPicker: DiscreteScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        cardListVIew = findViewById(R.id.forecast_view)

        lists = UsersList.get().forecasts
        listPicker = findViewById(R.id.forecast_city_picker)
        listPicker!!.setSlideOnFling(true)
        listPicker!!.adapter = CardAdapter(lists!!)
        listPicker!!.addOnItemChangedListener(this)
        listPicker!!.addScrollStateChangeListener(this)
        listPicker!!.scrollToPosition(2)
        listPicker!!.setItemTransitionTimeMillis(300)
        listPicker!!.setItemTransformer(ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        cardListVIew!!.setForecast(lists!![0])

    }

    override fun onCurrentItemChanged(holder: CardAdapter.ViewHolder?, position: Int) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
            cardListVIew!!.setForecast(lists!![position])
            holder.showText()
        }
    }

    override fun onScrollStart(holder: CardAdapter.ViewHolder, position: Int) {
        holder.hideText()
    }

    override fun onScroll(
            position: Float,
            currentIndex: Int, newIndex: Int,
            currentHolder: CardAdapter.ViewHolder?,
            newHolder: CardAdapter.ViewHolder?) {
        val current = lists!![currentIndex]
        if (newIndex >= 0 && newIndex < listPicker!!.adapter.itemCount) {
            val next = lists!![newIndex]
            cardListVIew!!.onScroll(1f - Math.abs(position), current, next)
        }
    }


    override fun onScrollEnd(holder: CardAdapter.ViewHolder, position: Int) {

    }
}
