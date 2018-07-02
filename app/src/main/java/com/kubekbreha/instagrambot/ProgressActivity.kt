package com.kubekbreha.instagrambot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProgressActivity : AppCompatActivity() {

    private var selectedListId : Int = -1
    private var action: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val bundle = intent.extras
        if (bundle != null) {
            selectedListId = bundle.get("listSelected") as Int
            action = bundle.get("action") as String
        }



    }
}
