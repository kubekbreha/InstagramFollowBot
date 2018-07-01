package com.kubekbreha.instagrambot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.R
import com.kubekbreha.instagrambot.UsersInList
import kotlinx.android.synthetic.main.activity_add_list.*
import kotlinx.android.synthetic.main.fragment_bottomsheet_add_user_to_list.*


class BottomNavigationDrawerFragmentAddUser: BottomSheetDialogFragment() {

    private var listId: Int = 0
    private var listName: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listId = arguments!!.getInt("listId")
        listName = arguments!!.getString("listName")
        return inflater.inflate(R.layout.fragment_bottomsheet_add_user_to_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_drawer_add_list_button.isAllCaps = false
        val user = UsersInList(listId, context!!)


        bottom_drawer_add_list_button.setOnClickListener{
            user.addUser("testUser")
        }

    }

}
