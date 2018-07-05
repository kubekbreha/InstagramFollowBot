package com.kubekbreha.instagrambot.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.adapters.ListsAdapter
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.UsersList
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists


class MainFragment : Fragment() {

    var listsArray: ArrayList<String> = ArrayList()
    private lateinit var recyclerViewList: RecyclerView
    private lateinit var relativeViewListEmpty: RelativeLayout
    private lateinit var database: DatabaseHandlerLists
    private lateinit var allLists: MutableList<UsersList>
    private lateinit var adapter: ListsAdapter

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        database = DatabaseHandlerLists(context!!)
        allLists = database.readData()

        recyclerViewList = view.findViewById(R.id.fragment_comment_recyclerView)
        relativeViewListEmpty = view.findViewById(R.id.fragment_main_comment_empty_relativeLayout)

        getLists()
        recyclerViewList.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter =  ListsAdapter(listsArray, context!!)
        recyclerViewList.adapter = adapter
        if(listsArray.isEmpty()){
            recyclerViewList.visibility = View.GONE
            relativeViewListEmpty.visibility = View.VISIBLE
        }else{
            recyclerViewList.visibility = View.VISIBLE
            relativeViewListEmpty.visibility = View.GONE
        }

        return view
    }


    private fun getLists() {
        allLists.forEach { oneList ->
            listsArray.add(oneList.name)
        }
    }


    override fun onResume() {
        super.onResume()
        adapter.resetDoubleClicked()
        val editor = context!!.getSharedPreferences("instagrambot_listId", Context.MODE_PRIVATE).edit()
        editor.putInt("listId", -1)
        editor.apply()
    }

}



