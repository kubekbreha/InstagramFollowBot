package com.kubekbreha.instagrambot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.adapters.ListsAdapter
import com.kubekbreha.instagrambot.fragments.BottomNavigationDrawerFragmentAddUser
import com.kubekbreha.instagrambot.util.DatabaseHandlerLists
import kotlinx.android.synthetic.main.activity_add_list.*
import org.jetbrains.anko.toast


class AddListActivity : AppCompatActivity() {

    private val ADD_NEW_LIST = -100

    var listsArray: ArrayList<String> = ArrayList()
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var relativeViewUsersEmpty: RelativeLayout
    private lateinit var allLists: MutableList<UsersList>

    private lateinit var oneListItem: UsersList
    private lateinit var database: DatabaseHandlerLists

    private var openedListId: Int = ADD_NEW_LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        database = DatabaseHandlerLists(this)

        val bundle = intent.extras
        if (bundle != null) {
            openedListId = bundle.get("openedListId") as Int
        }


        if (openedListId != ADD_NEW_LIST) {
            toast(openedListId.toString())
            activity_add_list_button.text = "Update"
            activity_add_list_button.isAllCaps = false
            oneListItem = database.readOneListData(openedListId)!!

            val editText = findViewById<View>(R.id.activity_add_list_editText) as EditText
            editText.setText(oneListItem!!.name, TextView.BufferType.EDITABLE)

            //load list of users in list
            recyclerViewUsers = findViewById(R.id.activity_add_list_recyclerView)
            relativeViewUsersEmpty = findViewById(R.id.add_activity_comment_empty_relativeLayout)

            //[JB 1.7.2018]TODO not sure if that will work
            listsArray = getPeoples(openedListId) as ArrayList<String>

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
                database.updateTask(UsersList(activity_add_list_editText.text.toString(), oneListItem.list, openedListId + 1))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }


        } else {
            activity_add_list_button.text = "Add"
            activity_add_list_button.isAllCaps = false
            activity_add_list_button.setOnClickListener {
                database.insertData(UsersList(activity_add_list_editText.text.toString(), "{\"list\":{}}"))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }


        //inflate add user bottom fragment
        activity_add_list_floatingActionButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("listId", oneListItem.id)
            bundle.putString("listName", oneListItem.name)
            val fragobj = BottomNavigationDrawerFragmentAddUser()
            fragobj.setArguments(bundle)
            fragobj.show(supportFragmentManager, fragobj.tag)
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        //[JB 1.7.2018]TODO That is no way close to what effectiveness means
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }


    fun getPeoples(listId: Int): MutableList<String> {
        val user = UsersInList(listId, this)
        return user.getUsers()
    }


}
