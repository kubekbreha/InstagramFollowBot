package com.kubekbreha.instagrambot.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.AddListActivity
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.list_item.view.*


class ListsAdapter(private val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listType.text = "#" + items[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AddListActivity::class.java)
            val b = Bundle()
            b.putInt("openedListId", position)
            intent.putExtras(b)
            context.startActivity(intent)
            (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            (context).finish()
        }

    }
}