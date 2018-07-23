package com.kubekbreha.instagramhelper.cards

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.kubekbreha.instagramhelper.R

class ListCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var layoutList: RelativeLayout
    var listView: RecyclerView
    var cardName: TextView
    var addButton: FloatingActionButton


    init {
        this.layoutList = itemView.findViewById(R.id.item_card_container_list) as RelativeLayout
        this.listView = itemView.findViewById(R.id.item_card_users_list) as RecyclerView
        this.cardName = itemView.findViewById(R.id.item_card_list_name) as TextView
        this.addButton = itemView.findViewById(R.id.item_card_fab) as FloatingActionButton
    }


    override fun onClick(v: View) {

    }
}
