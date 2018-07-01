package com.kubekbreha.instagrambot

import android.content.Context
import android.util.Log
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONObject


class UsersInList(private var id: Int, private var context: Context) {

    private var usersInList: MutableList<String> =  ArrayList()
    var database: DatabaseHandlerLists
    private lateinit var stringOfUseers: String


    init {
        database = DatabaseHandlerLists(context)
    }


    fun addUser(userName: String) {
        val oneList = database.readOneListData( id-1)
        context.toast( oneList!!.list)
        val json = JSONObject("{\"list\":[]}")


        val jsonArray = json.getJSONArray("list")


        if (jsonArray.length() !=0 ) {
            for (i in 0..jsonArray.length()) {
                Log.e("motherfuck", jsonArray.get(i).toString())
                usersInList.add(jsonArray.get(i).toString())
            }
        }

        usersInList.add(userName)

        json.put("list", usersInList)
        context.toast( json.toString())


        database.updateTask(UsersList(oneList.name, json.toString(), oneList.id))
    }

}

