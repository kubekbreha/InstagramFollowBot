package com.kubekbreha.instagramhelper.cards

import android.content.Intent
import android.media.MediaPlayer
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.kubekbreha.instagramhelper.MainActivity
import com.kubekbreha.instagramhelper.R
import android.support.v4.content.ContextCompat.startActivity
import android.app.Activity
import com.kubekbreha.instagramhelper.AddNewActivity


class CardAdapter(private val data: List<UsersListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var parentRecycler: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        parentRecycler = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val v: View

        when (viewType) {
            UsersListItem.ADD_NEW_TYPE -> {
                v = inflater.inflate(R.layout.item_card, parent, false)
                return AddCardHolder(v)
            }
            UsersListItem.PEOPLE_LIST_TYPE -> {
                v = inflater.inflate(R.layout.item_card, parent, false)
                return ListCardHolder(v)
            }
        }

        v = inflater.inflate(R.layout.item_card, parent, false)
        return AddCardHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val `object` = data[position]
        when (`object`.type) {
            UsersListItem.ADD_NEW_TYPE -> {

                (holder as AddCardHolder).layout.visibility = View.VISIBLE
                holder.addButton.setOnClickListener{
                    val intent = Intent(holder.addButton.context, AddNewActivity::class.java)
                    holder.addButton.context.startActivity(intent)
                    holder.addButton.context.startActivity(intent)
                    val activity = holder.addButton.context as Activity
                    activity.startActivity(intent)
                    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

            }

        }
    }





    override fun getItemCount(): Int {
        return data.size
    }


}
