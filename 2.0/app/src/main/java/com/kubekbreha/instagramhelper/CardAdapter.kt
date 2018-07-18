package com.kubekbreha.instagramhelper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CardAdapter(private val data: List<ListItem>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var parentRecycler: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        parentRecycler = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {

            itemView.findViewById<View>(R.id.item_card_container).setOnClickListener(this)
        }

        fun showText() {

        }

        fun hideText() {

        }

        override fun onClick(v: View) {
            parentRecycler!!.smoothScrollToPosition(adapterPosition)
        }
    }
}
