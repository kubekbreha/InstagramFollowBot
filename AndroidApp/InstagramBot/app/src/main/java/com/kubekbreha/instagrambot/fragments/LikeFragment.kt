package com.kubekbreha.instagrambot.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kubekbreha.instagrambot.R


class LikeFragment : Fragment() {

    companion object {
        fun newInstance(): LikeFragment = LikeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false)
    }


}


//                R.id.bottom_menu_comment -> getPostIDs("therock")
//                R.id.bottom_menu_like -> like(tagFeedClass.items[0].pk)