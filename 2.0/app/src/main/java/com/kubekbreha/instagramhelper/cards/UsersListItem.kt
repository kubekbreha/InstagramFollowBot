package com.kubekbreha.instagramhelper.cards


class UsersListItem(var type: Int, val listName: String, val gradient: Int) {
    companion object {
        val ADD_NEW_TYPE = 0
        val PEOPLE_LIST_TYPE = 1
    }

}
