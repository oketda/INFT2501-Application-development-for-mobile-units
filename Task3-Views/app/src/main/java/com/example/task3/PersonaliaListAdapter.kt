package com.example.task3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class PersonaliaListAdapter(
    mContext: Context,
    resource: Int,
    personalias: MutableList<Personalia>
)
    : ArrayAdapter<Personalia>(mContext, resource) {

    val mContext: Context = mContext
    val resource: Int = resource
    val personalias: MutableList<Personalia> = personalias

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val name: String? = personalias.get(position).name
        val date: Calendar = personalias.get(position).dateOfBirth


        val day: String = date.get(Calendar.DAY_OF_MONTH).toString()
        val month: String = date.get(Calendar.MONTH).toString()
        val year: String = date.get(Calendar.YEAR).toString()

        val dateOfBirth = "$day/$month/$year"

        val inflater: LayoutInflater = LayoutInflater.from(mContext)
        val realConvertView = inflater.inflate(resource, parent, false)

        val tvName: TextView = realConvertView.findViewById(R.id.list_name) as TextView
        val tvDate: TextView = realConvertView.findViewById(R.id.list_date) as TextView

        tvName.setText(name)
        tvDate.setText(dateOfBirth)


        return realConvertView
    }

    override fun getCount(): Int {
        return personalias.size
    }
}