package com.kubekbreha.instagrambot

import android.content.Context
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import dev.niekirk.com.instagram4android.requests.payload.InstagramUserSummary
import org.jetbrains.anko.toast
import org.json.JSONObject


class UsersInList(private var id: Int, private var context: Context) {

    private var usersInList: MutableList<String> = ArrayList()
    var database: DatabaseHandlerLists
    private lateinit var stringOfUseers: String


    init {
        database = DatabaseHandlerLists(context)
    }


    fun addUser(userName: String) {
        val oneList = database.readOneListData(id - 1)
        val json = JSONObject(oneList!!.list)

        val jsonObj = json.getJSONObject("list")


        if (jsonObj.length() != 0) {
            for (i in 0 until jsonObj.length()) {
                usersInList.add(jsonObj.getString((i).toString()))
            }
        }

        usersInList.add(userName)

        if (jsonObj.length() + 1 != 0) {
            for (i in 0..jsonObj.length()) {
                jsonObj.put(i.toString(), usersInList[i])
            }
        }

        database.updateTask(UsersList(oneList.name, json.toString(), oneList.id))
    }


    fun addUsersList(userList: MutableList<InstagramUserSummary>) {
        val oneList = database.readOneListData(id - 1)
        val json = JSONObject(oneList!!.list)

        val jsonObj = json.getJSONObject("list")


        if (jsonObj.length() != 0) {
            for (i in 0 until jsonObj.length()) {
                usersInList.add(jsonObj.getString((i).toString()))
            }
        }

        for (item in userList) {
            usersInList.add(item.username)

        }

        if (jsonObj.length() + 1 != 0) {
            for (i in 0..(jsonObj.length() + usersInList.size - 1)) {
                jsonObj.put(i.toString(), usersInList[i])
            }
        }

        database.updateTask(UsersList(oneList.name, json.toString(), oneList.id))
    }


    fun getUsers(): MutableList<String> {
        val oneList = database.readOneListData(id)
        val json = JSONObject(oneList!!.list)

        val jsonObj = json.getJSONObject("list")

        if (jsonObj.length() != 0) {
            for (i in 0 until jsonObj.length()) {
                usersInList.add(jsonObj.getString((i).toString()))
            }
        }

        return usersInList
    }

}

