package com.kubekbreha.instagramhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.user_in_list.view.*

class ItemInCardViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val userName = view.user_in_list_name
}