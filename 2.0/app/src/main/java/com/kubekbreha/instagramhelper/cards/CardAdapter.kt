package com.kubekbreha.instagramhelper.cards

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kubekbreha.instagramhelper.R
import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.kubekbreha.instagramhelper.AddNewActivity


class CardAdapter(private val data: List<UsersListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var parentRecycler: RecyclerView? = null
    val users: ArrayList<String> = ArrayList()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        parentRecycler = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v: View

        return if (viewType == UsersListItem.ADD_NEW_TYPE) {
            v = inflater.inflate(R.layout.item_card, parent, false)
            AddCardHolder(v)
        } else {
            v = inflater.inflate(R.layout.item_card, parent, false)
            ListCardHolder(v)
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val `object` = data[position]
        when (`object`.type) {

            UsersListItem.ADD_NEW_TYPE -> {
                (holder as AddCardHolder).layoutAdd.visibility = View.VISIBLE
                holder.addButton.setOnClickListener{
                    val intent = Intent(holder.addButton.context, AddNewActivity::class.java)
                    val activity = holder.addButton.context as Activity
                    activity.startActivity(intent)
                    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }

            UsersListItem.PEOPLE_LIST_TYPE -> {
                (holder as ListCardHolder).layoutList.visibility = View.VISIBLE
                addUsers()
                holder.listView.layoutManager = LinearLayoutManager(holder.listView.context)
                holder.listView.adapter = AnimalAdapter(users, holder.listView.context)
            }

        }
    }


    fun addUsers() {
        users.add("dog")
        users.add("cat")
        users.add("owl")
        users.add("cheetah")
        users.add("bird")
        users.add("snake")
        users.add("lizard")
        users.add("hamster")
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            0 -> UsersListItem.ADD_NEW_TYPE
            1 -> UsersListItem.PEOPLE_LIST_TYPE
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
