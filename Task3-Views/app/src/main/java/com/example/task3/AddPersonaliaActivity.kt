package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class AddPersonaliaActivity : Activity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_personalia)

        initAddPersonaliaButton()
        initCancelButton()
    }

    private fun initAddPersonaliaButton() {
        val button = findViewById<Button>(R.id.add_personalia)
        button.setOnClickListener {
            val nameTextView: TextView = findViewById(R.id.name_input)
            val name: String = nameTextView.text.toString()
            val dateTextView: TextView = findViewById(R.id.date_input)
            val date: String = dateTextView.text.toString()

            if (name.isEmpty() || date.length !== 8) {
                Log.e("INFT", "Name and date must have valid inputs")
                return@setOnClickListener
            }

            val intent = Intent()
            intent.putExtra("name", name)
            intent.putExtra("date", date)

            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initCancelButton() {
        val button = findViewById<Button>(R.id.cancel)
        button.setOnClickListener { finish() }
    }


}