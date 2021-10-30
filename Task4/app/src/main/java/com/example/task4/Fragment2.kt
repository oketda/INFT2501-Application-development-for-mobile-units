package com.example.task4

import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment

class Fragment2 : Fragment() {
    private var descriptions: Array<String> = arrayOf()
    private var pictures: TypedArray? = null
    private lateinit var imageView: ImageView

    fun Fragment2() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        descriptions = resources.getStringArray(R.array.picture_descriptions)
        pictures = resources.obtainTypedArray(R.array.pictures)

        val view = layoutInflater.inflate(R.layout.fragment2, container)

        setImage(view, 0)
        setText(view, 0)

        return view
    }

    fun setImage(view: View, index: Int) {

        imageView = view.findViewById<ImageView>(R.id.picture)
        imageView.setImageDrawable(pictures?.getDrawable(index))
    }

    fun setText(view: View, index: Int) {
        val textView = view.findViewById<TextView>(R.id.picture_description)
        textView.setText(descriptions[index])
    }
}
