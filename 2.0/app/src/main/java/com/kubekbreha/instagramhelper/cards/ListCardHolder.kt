package com.kubekbreha.instagramhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import com.kubekbreha.instagramhelper.R

class ListCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var layoutList: RelativeLayout
    var listView: RecyclerView


    init {
        this.layoutList = itemView.findViewById(R.id.item_card_container_list) as RelativeLayout
        this.listView = itemView.findViewById(R.id.item_card_users_list) as RecyclerView
    }


    override fun onClick(v: View) {

    }
}
