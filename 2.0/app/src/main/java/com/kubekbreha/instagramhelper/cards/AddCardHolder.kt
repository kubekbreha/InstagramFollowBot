package com.kubekbreha.instagramhelper.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.kubekbreha.instagramhelper.R
import org.jetbrains.anko.toast

class AddCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var layoutAdd: RelativeLayout
    var addButton: ImageButton

    init {
        this.layoutAdd = itemView.findViewById(R.id.item_card_container_add) as RelativeLayout
        this.addButton = itemView.findViewById(R.id.item_card_add_new) as ImageButton
    }


    override fun onClick(v: View) {

    }
}
