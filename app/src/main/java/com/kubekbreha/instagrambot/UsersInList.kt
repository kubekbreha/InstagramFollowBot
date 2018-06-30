package com.kubekbreha.instagrambot

import android.content.Context
import android.util.Log
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*
import org.json.JSONObject


class UsersInList {

    private var id: Int = 0
    private var context: Context
    private var usersInList: MutableList<String> =  ArrayList()
    var database: DatabaseHandlerLists
    private lateinit var stringOfUseers: String


    constructor(id: Int, context: Context) {
        this.id = id
        this.context = context

        database = DatabaseHandlerLists(context)
    }

    fun readUsers() {
        val database = DatabaseHandlerLists(context)
        val oneListItem = database.readOneListData(id)

        val stringOfUseers = oneListItem!!.list
    }

    fun addUser(userName: String, listName: String) {
        usersInList.add("first")
        usersInList.add("second")


        val json = JSONObject("""{"name":"test name", "age":25}""")
        json.put("users", usersInList as Any)

        Log.e("motherfuck", json.get("users").toString())
    }


}