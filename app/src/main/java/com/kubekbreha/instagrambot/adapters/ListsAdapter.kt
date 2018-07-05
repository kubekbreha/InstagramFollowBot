package com.kubekbreha.instagrambot.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.AddListActivity
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.list_item.view.*


class ListsAdapter(private val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var doubleClicked = 0
    var markedItem = -100
    private var itemsInAdapter: MutableList<CardView> = mutableListOf()


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.listType.text = "#" + items[position]

        holder.itemView.setOnClickListener {

            //save to application class picked list
            val editor = context.getSharedPreferences("instagrambot_listId", Context.MODE_PRIVATE).edit()
            editor.putInt("listId", position)
            editor.putInt("listIdSize", itemCount)
            editor.apply()

            doubleClicked++
            if (position == markedItem) {
                if (doubleClicked == 2) {
                    resetDoubleClicked()

                    val intent = Intent(context, AddListActivity::class.java)
                    val b = Bundle()
                    b.putInt("openedListId", position)
                    intent.putExtras(b)
                    context.startActivity(intent)
                    (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    (context).finish()

                    var card = holder.itemView.list_item_cardView
                    card.setCardBackgroundColor(context.resources.getColor(R.color.white))
                }
            } else {
                var card = holder.itemView.list_item_cardView
                card.setCardBackgroundColor(context.resources.getColor(R.color.colorAccent))

                itemsInAdapter.add(holder.itemView.list_item_cardView)
                markedItem = position
                if (doubleClicked == 2) doubleClicked = 0

                if (itemsInAdapter.size >= 2) {
                    Log.i("ADAPTERTAG", "IM IN")
                    var card = itemsInAdapter[itemsInAdapter.size - 2]
                    card.setCardBackgroundColor(context.resources.getColor(R.color.white))
                }
            }
        }
    }


    fun resetDoubleClicked() {
        doubleClicked = 0
        markedItem = -100
        if(!itemsInAdapter.isEmpty()) {
            var card = itemsInAdapter[itemsInAdapter.size - 1]
            card.setCardBackgroundColor(context.resources.getColor(R.color.white))
        }
    }


}