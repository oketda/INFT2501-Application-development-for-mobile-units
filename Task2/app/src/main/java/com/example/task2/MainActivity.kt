package com.example.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val requestCode: Int = 1
    private var limit = 100
    private var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartRandomNumberActivity(v: View?) {
        val intent = Intent("task2.RandomNumberActivity")
        intent.putExtra("limit", limit)
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                randomNumber = data.getIntExtra("random_number", randomNumber)
                val text = findViewById<View>(R.id.textView) as TextView
                text.setText("Recieved number is: $randomNumber")
            }
        }
    }

    fun onClickStartMathTestActivity(v: View?) {
        val intent = Intent("task2.MathTestActivity")
        startActivity(intent)
    }

}