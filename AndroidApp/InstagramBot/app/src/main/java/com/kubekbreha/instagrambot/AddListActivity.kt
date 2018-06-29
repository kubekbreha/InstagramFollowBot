package com.kubekbreha.instagrambot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*
import android.widget.TextView
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.adapters.ListsAdapter
import com.kubekbreha.instagrambot.adapters.PeopleInListAdapter
import org.jetbrains.anko.toast


class AddListActivity : AppCompatActivity() {

    private val ADD_NEW_LIST = -100

    var listsArray: ArrayList<String> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: UsersList

    private var openedListId: Int = ADD_NEW_LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        val database = DatabaseHandlerLists(this)

        val bundle = intent.extras
        if (bundle != null) {
            openedListId = bundle.get("openedListId") as Int
        }


        if (openedListId != ADD_NEW_LIST) {
            toast(openedListId.toString())
            activity_add_list_button.text = "Update"
            activity_add_list_button.isAllCaps = false
            val oneListItem = database.readOneListData(openedListId)

            val editText = findViewById<View>(R.id.activity_add_list_editText) as EditText
            editText.setText(oneListItem?.name, TextView.BufferType.EDITABLE)

            activity_add_list_button.setOnClickListener {
                database.updateTask(UsersList(activity_add_list_editText.text.toString(), "", openedListId+1))
            }

        } else {
            activity_add_list_button.text = "Add"
            activity_add_list_button.isAllCaps = false
            activity_add_list_button.setOnClickListener {
                database.insertData(UsersList(activity_add_list_editText.text.toString(), ""))
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        //TODO That is no way close to effectiveness
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }


    fun getPeoples() {
        listsArray.add("test name ")
    }


}
