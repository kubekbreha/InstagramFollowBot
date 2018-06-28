package com.kubekbreha.instagrambot.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.adapters.ListsAdapter
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.UsersList
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists


class MainFragment : Fragment() {

    var listsArray: ArrayList<String> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: DatabaseHandlerLists
    private lateinit var allLists: MutableList<UsersList>
    private var allowRefresh = true

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        database = DatabaseHandlerLists(context!!)
        allLists = database.readData()

        recyclerView = view.findViewById(R.id.fragment_comment_recyclerView)
        getLists()
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = ListsAdapter(listsArray, context!!)

        return view
    }

    override fun onResume() {
        super.onResume()
        database = DatabaseHandlerLists(context!!)
        allLists = database.readData()
        getLists()
        recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = ListsAdapter(listsArray, context!!)
    }


    // Adds animals to the empty animals ArrayList
    fun getLists() {
        allLists.forEach { oneList ->
            listsArray.add(oneList.name)
        }
    }

}



