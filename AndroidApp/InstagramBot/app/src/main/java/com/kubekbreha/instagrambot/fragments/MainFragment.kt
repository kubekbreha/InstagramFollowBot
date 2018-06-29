package com.kubekbreha.instagrambot.fragments


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
    private lateinit var recyclerViewListEmpty: RelativeLayout
    private lateinit var database: DatabaseHandlerLists
    private lateinit var allLists: MutableList<UsersList>

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        database = DatabaseHandlerLists(context!!)
        allLists = database.readData()

        recyclerViewList = view.findViewById(R.id.fragment_comment_recyclerView)
        recyclerViewList = view.findViewById(R.id.fragment_comment_recyclerView)
        recyclerViewListEmpty = view.findViewById(R.id.fragment_comment_empty_relativeLayout)

        getLists()
        recyclerViewList.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerViewList.adapter = ListsAdapter(listsArray, context!!)
        if(listsArray.isEmpty()){
            recyclerViewList.visibility = View.GONE
            recyclerViewListEmpty.visibility = View.VISIBLE
        }else{
            recyclerViewList.visibility = View.VISIBLE
            recyclerViewListEmpty.visibility = View.GONE
        }

        return view
    }


    fun getLists() {
        allLists.forEach { oneList ->
            listsArray.add(oneList.name)
        }
    }

}



