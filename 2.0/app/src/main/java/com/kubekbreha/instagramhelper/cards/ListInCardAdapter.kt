package com.kubekbreha.instagramhelper.cards


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubekbreha.instagramhelper.R

class ListInCardAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ItemInCardViewHolder>() {

    override fun onBindViewHolder(holder: ItemInCardViewHolder, position: Int) {
        holder.userName?.text = items.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInCardViewHolder {
        return ItemInCardViewHolder(LayoutInflater.from(context).inflate(R.layout.user_in_list, parent, false))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

