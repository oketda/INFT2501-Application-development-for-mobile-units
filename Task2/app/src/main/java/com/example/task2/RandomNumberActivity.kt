package com.example.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class RandomNumberActivity : Activity() {
    private var limit = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)
        limit = intent.getIntExtra("limit", limit)
        showRandomNumber()
    }

    private fun showRandomNumber() {
        val randomNumber = (0..limit).random()
        /*Toast.makeText(applicationContext,
            "Random number between 0 and 100 is: $randomNumber",
            Toast.LENGTH_SHORT ).show()*/
        finnishActivity(randomNumber)
    }

    private fun finnishActivity(value: Int) {
        setResult(RESULT_OK, Intent().putExtra("random_number", value))
        finish()
    }
}