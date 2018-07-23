package com.kubekbreha.instagramhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.kubekbreha.instagramhelper.R
import org.jetbrains.anko.toast

class AddCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

     var addButton: ImageButton

    init {
        itemView.findViewById<View>(R.id.item_card_container).setOnClickListener(this)
        this.addButton = itemView.findViewById(R.id.item_card_add_new) as ImageButton
    }



    override fun onClick(v: View) {
        itemView.context.toast(adapterPosition.toString())
    }
}
