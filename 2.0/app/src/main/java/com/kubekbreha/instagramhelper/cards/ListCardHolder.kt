package com.kubekbreha.instagramhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kubekbreha.instagramhelper.R
import org.jetbrains.anko.toast

class ListCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.findViewById<View>(R.id.item_card_container).setOnClickListener(this)
    }

    fun showText() {

    }

    fun hideText() {

    }

    override fun onClick(v: View) {
        itemView.context.toast(adapterPosition.toString())
    }
}