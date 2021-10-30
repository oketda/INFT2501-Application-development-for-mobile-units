package com.example.task3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable
import java.util.*

class MainActivity : AppCompatActivity() {
    val personalias: MutableList<Personalia> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = Calendar.getInstance()
        date.set(1999, 4, 20)
        personalias.add(Personalia("Herman", date))

        initShowPersonaliasButton()
        initAddPersonaliaButton()
    }

    private fun initShowPersonaliasButton() {
        val button = findViewById<Button>(R.id.show_personalias)
        button.setOnClickListener {
            val intent = Intent("task3.ShowPersonaliaActivity")
            intent.putExtra("personalias", personalias as Serializable)
            resultLauncherEditPersonalia.launch(intent)
        }
    }

    private fun initAddPersonaliaButton() {
        val button = findViewById<Button>(R.id.add_personalia)
        button.setOnClickListener {
            resultLauncherAddPersonalia.launch(Intent("task3.AddPersonalia"))
        }
    }

    var resultLauncherEditPersonalia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val name: String? = data.getStringExtra("name")
                val date: String? = data.getStringExtra("date")
                val position: Int? = data.getStringExtra("position")?.toIntOrNull()

                val day: Int = date?.subSequence(0, 2).toString().toInt()
                val month: Int = date?.subSequence(2, 4).toString().toInt()
                val year: Int = date?.takeLast(4).toString().toInt()
                val dateOfBirth = Calendar.getInstance()
                dateOfBirth.set(year, month, day)

                if (position != null) {
                    personalias.get(position).name = name
                    personalias.get(position).dateOfBirth = dateOfBirth
                }
                Toast.makeText(applicationContext,
                    "Personalia for $name updated",
                    Toast.LENGTH_SHORT ).show()
            }
        }
    }

    var resultLauncherAddPersonalia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val name: String? = data.getStringExtra("name")
                val date: String? = data.getStringExtra("date")

                val day: Int = date?.subSequence(0, 2).toString().toInt()
                val month: Int = date?.subSequence(2, 4).toString().toInt()
                val year: Int = date?.takeLast(4).toString().toInt()
                val dateOfBirth = Calendar.getInstance()
                dateOfBirth.set(year, month, day)

                personalias.add(Personalia(name, dateOfBirth))
                Toast.makeText(applicationContext,
                    "Personalia for $name with birthdate $day/$month/$year added.",
                    Toast.LENGTH_SHORT ).show()
            }
        }
    }
}