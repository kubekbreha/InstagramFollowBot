package com.kubekbreha.instagrambot

import android.content.Context
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists


class UsersInList {

    private var id : Int = 0
    private var context: Context
    private lateinit var usersInList: MutableList<String>

    constructor(id: Int, context: Context) {
        this.id = id
        this.context = context
    }

    fun readUsers(openedListId: Int){
        val database = DatabaseHandlerLists(context)
        val oneListItem = database.readOneListData(openedListId)

        val stringOfUseers = oneListItem!!.list
    }


    fun addUser(userName: String){
        usersInList.add(userName)    
    }

}