package com.example.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MathTestActivity: ComponentActivity() {
    private var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_test)
    }

    fun onClickAdd(v: View?) {
        val numberTextView: TextView = findViewById(R.id.number)
        val number: String = numberTextView.text.toString()

        val numberTextView2: TextView = findViewById(R.id.number2)
        val number2: String = numberTextView2.text.toString()

        val answerTextView: TextView = findViewById(R.id.answer)
        val answer: String = answerTextView.text.toString()

        if (number.toInt() + number2.toInt() == answer.toInt()) {
            correctAnswer()
        } else {
            wrongAnswer(number.toInt()+number2.toInt())
        }
        newNumbers()
    }

    fun onClickMultiply(v: View?) {
        val numberTextView: TextView = findViewById(R.id.number)
        val number: String = numberTextView.text.toString()

        val numberTextView2: TextView = findViewById(R.id.number2)
        val number2: String = numberTextView2.text.toString()

        val answerTextView: TextView = findViewById(R.id.answer)
        val answer: String = answerTextView.text.toString()

        if (number.toInt() * number2.toInt() == answer.toInt()) {
            correctAnswer()
        } else {
            wrongAnswer(number.toInt()*number2.toInt())
        }
        newNumbers()
    }

    private fun correctAnswer() {
        Toast.makeText(applicationContext,
            "Riktig!",
            Toast.LENGTH_SHORT ).show()
    }

    private fun wrongAnswer(correctAnswer: Int) {
        Toast.makeText(applicationContext,
            "Feil. Riktig svar er: $correctAnswer",
            Toast.LENGTH_SHORT ).show()
    }

    private fun newNumbers() {
        val topLimitTextView: TextView = findViewById(R.id.top_limit)
        val topLimit: String = topLimitTextView.text.toString()

        val intent = Intent("task2.RandomNumberActivity")
        intent.putExtra("limit", topLimit.toInt())
        getRandomNumber.launch(intent)
        getRandomNumber2.launch(intent)
    }

    var getRandomNumber = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                var number = data.getIntExtra("random_number", randomNumber)
                val numberTextView: TextView = findViewById(R.id.number)
                numberTextView.text = "$number"
            }
        }
    }

    var getRandomNumber2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                var number = data.getIntExtra("random_number", randomNumber)
                val numberTextView: TextView = findViewById(R.id.number2)
                numberTextView.text = "$number"
            }
        }
    }
}