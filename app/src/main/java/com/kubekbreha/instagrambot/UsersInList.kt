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
        val json = JSONObject(oneList!!.list)

        val jsonObj = json.getJSONObject("list")


        if (jsonObj.length() !=0 ) {
            for (i in 0 until jsonObj.length()) {
                usersInList.add(jsonObj.getString((i).toString()))
            }
        }


        usersInList.add(userName)

        if (jsonObj.length()+1 !=0 ) {
            for (i in 0..jsonObj.length()) {
                jsonObj.put(i.toString(), usersInList[i])
            }
        }
        
        context.toast( json.toString())
        database.updateTask(UsersList(oneList.name, json.toString(), oneList.id))
    }

}

