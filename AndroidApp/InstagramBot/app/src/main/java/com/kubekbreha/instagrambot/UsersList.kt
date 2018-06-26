package com.kubekbreha.instagrambot


class UsersList {

    var id : Int = 0
    lateinit var name: String
    lateinit var list: String


    constructor(name: String, list: String) {
        this.name = name
        this.list = list
    }

    constructor()
}