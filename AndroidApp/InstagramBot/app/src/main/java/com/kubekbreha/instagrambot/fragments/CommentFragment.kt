package com.kubekbreha.instagrambot.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kubekbreha.instagrambot.ListsAdapter
import com.kubekbreha.instagrambot.R
import kotlinx.android.synthetic.main.fragment_comment.*


class CommentFragment : Fragment() {

    val animals: ArrayList<String> = ArrayList()
    private lateinit var recyclerView : RecyclerView


    companion object {
        fun newInstance(): CommentFragment = CommentFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        recyclerView = view.findViewById(R.id.fragment_comment_recyclerView)
        addLists()
        recyclerView.layoutManager = LinearLayoutManager(context)
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ListsAdapter(animals, context!!)

        return view
    }


    // Adds animals to the empty animals ArrayList
    fun addLists() {
        animals.add("dog")
        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("lion")
        animals.add("tiger")
        animals.add("horse")
        animals.add("frog")
        animals.add("fish")
        animals.add("shark")
        animals.add("turtle")
        animals.add("elephant")
        animals.add("cow")
        animals.add("beaver")
        animals.add("bison")
        animals.add("porcupine")
        animals.add("rat")
        animals.add("mouse")
        animals.add("goose")
        animals.add("deer")
        animals.add("fox")
        animals.add("moose")
        animals.add("buffalo")
        animals.add("monkey")
        animals.add("penguin")
        animals.add("parrot")
    }




}
