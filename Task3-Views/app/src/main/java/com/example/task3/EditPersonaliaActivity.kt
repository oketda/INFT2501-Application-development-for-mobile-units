package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class EditPersonaliaActivity: Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_personalia)

        val personalia: Personalia = intent.getSerializableExtra("personalia") as Personalia
        val position: Int? = intent.getStringExtra("position")?.toIntOrNull()

        val nameTextView: TextView = findViewById(R.id.name_input)
        val dateTextView: TextView = findViewById(R.id.date_input)

        val day: String = personalia.dateOfBirth.get(Calendar.DAY_OF_MONTH).toString()
        var month: String = personalia.dateOfBirth.get(Calendar.MONTH).toString()
        val year: String = personalia.dateOfBirth.get(Calendar.YEAR).toString()

        if (month.length == 1) {
            month = "0" + month
        }

        nameTextView.setText(personalia.name)
        dateTextView.setText("$day$month$year")
        initSave(position)
    }

    fun initSave(position: Int?) {
        val button: Button = findViewById<Button>(R.id.save)
        button.setOnClickListener {
            val nameTextView: TextView = findViewById(R.id.name_input)
            val name: String = nameTextView.text.toString()
            val dateTextView: TextView = findViewById(R.id.date_input)
            val date: String = dateTextView.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("date", date)
            resultIntent.putExtra("position", position.toString())
            setResult(1, resultIntent)
            finish()
        }
    }
}