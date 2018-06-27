package com.kubekbreha.instagrambot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*

class AddListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        val database = DatabaseHandlerLists(this)

        activity_add_list_button.setOnClickListener{
            database.insertData(UsersList(activity_add_list_editText.text.toString(), ""))
        }

    }
}