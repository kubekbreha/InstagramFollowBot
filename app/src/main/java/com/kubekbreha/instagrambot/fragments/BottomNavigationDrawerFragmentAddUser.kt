package com.kubekbreha.instagrambot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.fragment_bottomsheet_add_user_to_list.*


class BottomNavigationDrawerFragmentAddUser: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet_add_user_to_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_drawer_add_list_button.setOnClickListener{
            //add this to users list
            //bottom_drawer_list_editText.text.toString()
        }

    }

}
