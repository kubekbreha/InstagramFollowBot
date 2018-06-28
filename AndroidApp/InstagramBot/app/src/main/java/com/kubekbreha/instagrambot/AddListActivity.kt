package com.kubekbreha.instagrambot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*
import android.widget.TextView
import android.view.View
import android.widget.EditText
import org.jetbrains.anko.toast


class AddListActivity : AppCompatActivity() {

    var openedListId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        val database = DatabaseHandlerLists(this)

        val bundle = intent.extras
        openedListId = bundle.get("openedListId") as Int

        val oneListItem = database.readOneListData(openedListId)

        val editText = findViewById<View>(R.id.activity_add_list_editText) as EditText
        editText.setText(oneListItem?.name, TextView.BufferType.EDITABLE)

        activity_add_list_button.setOnClickListener{
            database.updateTask(UsersList(activity_add_list_editText.text.toString(), "", openedListId))
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


}
