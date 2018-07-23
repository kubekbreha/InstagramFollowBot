package com.kubekbreha.instagramhelper.cards


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kubekbreha.instagramhelper.R
import kotlinx.android.synthetic.main.user_in_list.view.*

class AnimalAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAnimalType?.text = items.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_in_list, parent, false))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.user_in_list_name
}