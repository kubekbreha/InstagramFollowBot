package com.kubekbreha.instagramhelper.cards

import java.util.Arrays


class UsersListItemsHandler private constructor() {

    val lists: List<UsersListItem>
        get() = Arrays.asList(
                UsersListItem( 1,"0", 0),
                UsersListItem(1, "1", 1),
                UsersListItem(1, "2", 2),
                UsersListItem(1, "3", 3),
                UsersListItem(0, "4", 4))




    companion object {
        fun get(): UsersListItemsHandler {
            return UsersListItemsHandler()
        }
    }
}
