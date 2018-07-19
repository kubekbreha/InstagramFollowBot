package com.kubekbreha.instagramhelper

import java.util.Arrays


class UsersListItemsHandler private constructor() {

    val lists: List<UsersListItem>
        get() = Arrays.asList(
                UsersListItem("0", 0),
                UsersListItem("1", 1),
                UsersListItem("2", 2),
                UsersListItem("3", 3),
                UsersListItem("4", 4))

    companion object {


        fun get(): UsersListItemsHandler {
            return UsersListItemsHandler()
        }
    }
}
