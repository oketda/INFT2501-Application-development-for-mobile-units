package com.example.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {

    private val network: HttpWrapper = HttpWrapper(URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInfoButton()
        initCheckNumberButton()
    }

    private fun initInfoButton() {
        val button = findViewById<Button>(R.id.info_btn)
        button.setOnClickListener {
            val nameTextView: TextView = findViewById(R.id.name)
            val name: String = nameTextView.text.toString()
            val bankAccountTextView: TextView = findViewById(R.id.bank_account)
            val bankAccount: String = bankAccountTextView.text.toString()
            val map: Map<String, String> = mapOf(
                "navn" to name,
                "kortnummer" to bankAccount
            )

            performRequest(HTTP.GET, map)
        }
    }

    private fun initCheckNumberButton() {
        val button = findViewById<Button>(R.id.number_btn)
        button.setOnClickListener {
            val numberTextView: TextView = findViewById(R.id.number)
            val number = numberTextView.text.toString()

            val map: Map<String, String> = mapOf(
                "tall" to number
            )

            performRequest(HTTP.GET, map)
        }
    }

    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> network.post(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }

            // Endre UI på hovedtråden
            MainScope().launch {
                setResult(response)
            }
        }
    }

    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.result).text = response
    }
}