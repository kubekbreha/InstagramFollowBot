package com.kubekbreha.instagrambot

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
import com.kubekbreha.instagrambot.fragments.BottomNavigationDrawerFragmentAccountSettings
import com.kubekbreha.instagrambot.fragments.BottomNavigationDrawerFragmentAddUser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class AddListActivity : AppCompatActivity() {

    private val ADD_NEW_LIST = -100

    var listsArray: ArrayList<String> = ArrayList()
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var relativeViewUsersEmpty: RecyclerView
    private lateinit var allLists: MutableList<UsersList>


    private var openedListId: Int = ADD_NEW_LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        val database = DatabaseHandlerLists(this)

        val bundle = intent.extras
        if (bundle != null) {
            openedListId = bundle.get("openedListId") as Int
        }

        //inflate add user bottom fragment
        activity_add_list_floatingActionButton.setOnClickListener {
            val bottomNavDrawerFragment = BottomNavigationDrawerFragmentAddUser()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
        }


        if (openedListId != ADD_NEW_LIST) {
            toast(openedListId.toString())
            activity_add_list_button.text = "Update"
            activity_add_list_button.isAllCaps = false
            val oneListItem = database.readOneListData(openedListId)

            val editText = findViewById<View>(R.id.activity_add_list_editText) as EditText
            editText.setText(oneListItem?.name, TextView.BufferType.EDITABLE)

            //load list of users in list
            recyclerViewUsers = findViewById(R.id.activity_add_list_recyclerView)
            relativeViewUsersEmpty = findViewById(R.id.add_activity_comment_empty_relativeLayout)
            getPeoples(oneListItem!!)
            recyclerViewUsers.layoutManager = LinearLayoutManager(this)
            //recyclerViewUsers.layoutManager = GridLayoutManager(context, 2)
            recyclerViewUsers.adapter = ListsAdapter(listsArray, this)
            if (listsArray.isEmpty()) {
                recyclerViewUsers.visibility = View.GONE
                relativeViewUsersEmpty.visibility = View.VISIBLE
            } else {
                recyclerViewUsers.visibility = View.VISIBLE
                relativeViewUsersEmpty.visibility = View.GONE
            }

            activity_add_list_button.setOnClickListener {
                database.updateTask(UsersList(activity_add_list_editText.text.toString(), "", openedListId + 1))
            }


        } else {
            activity_add_list_button.text = "Add"
            activity_add_list_button.isAllCaps = false
            activity_add_list_button.setOnClickListener {
                database.insertData(UsersList(activity_add_list_editText.text.toString(), ""))
            }
        }


        activity_add_list_floatingActionButton.setOnClickListener {

        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        //TODO That is no way close to what effectiveness means
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }


    fun getPeoples(oneItemList: UsersList) {

    }


}
