package com.kubekbreha.instagrambot.util

import android.app.Application

class StoreListAplicationClass: Application() {

    private var data: Int? = 0

    fun getData(): Int? {
        return data
    }

    fun setData(data: Int) {
        this.data = data
    }

}
