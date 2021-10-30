package com.example.task4

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class Fragment1 : ListFragment() {
    private var titles: Array<String> = arrayOf()

    fun Fragment1() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titles = resources.getStringArray(R.array.picture_titles)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1,
                android.R.id.text1, titles)
        }
    }
}