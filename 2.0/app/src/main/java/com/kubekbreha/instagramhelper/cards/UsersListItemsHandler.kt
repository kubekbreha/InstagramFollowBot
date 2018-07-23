package com.kubekbreha.instagramhelper.cards

import java.util.Arrays


class UsersListItemsHandler private constructor() {

    val lists: List<UsersListItem>
        get() = Arrays.asList(
                UsersListItem("0", 0, 1),
                UsersListItem("1", 1, 1),
                UsersListItem("2", 2, 1),
                UsersListItem("3", 3, 1),
                UsersListItem("4", 4, 3))

    companion object {


        fun get(): UsersListItemsHandler {
            return UsersListItemsHandler()
        }
    }
}
