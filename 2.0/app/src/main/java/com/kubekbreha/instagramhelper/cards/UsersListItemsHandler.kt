package com.kubekbreha.instagramhelper.cards

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kubekbreha.instagrambot.loginActivity.User


class UsersListItemsHandler(listName: String) {

    var mDatabase: DatabaseReference? = null
    var lists: MutableList<UsersListItem>? = null


    init {
        lists = mutableListOf()
        mDatabase = FirebaseDatabase.getInstance().reference
        lists!!.add(UsersListItem(0, "addNew", 2))
    }


    fun addToCardLists(type: Int, listName: String, gradient: Int) {
        lists!!.add(0,UsersListItem(type,listName, gradient))
    }

    fun addCardToDatabase(type: Int, listName: String, gradient: Int) {
        val userItem = UsersListItem(type,listName, gradient)
        mDatabase!!.child(User.instagramUser.username).child(listName).setValue(userItem)
    }

    fun getCardsFromDatabase(){

    }

    fun getCardsList(): MutableList<UsersListItem>? {
        return lists
    }
}
