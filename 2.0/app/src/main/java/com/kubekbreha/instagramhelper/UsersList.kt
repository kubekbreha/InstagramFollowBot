package com.kubekbreha.instagramhelper

import java.util.Arrays


class UsersList private constructor() {

    val forecasts: List<ListItem>
        get() = Arrays.asList(
                ListItem("0", 0),
                ListItem("1", 1),
                ListItem("2", 2),
                ListItem("3", 3),
                ListItem("4", 4))

    companion object {


        fun get(): UsersList {
            return UsersList()
        }
    }
}
