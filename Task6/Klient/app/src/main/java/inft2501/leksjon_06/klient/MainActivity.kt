package inft2501.leksjon_06.klient

import android.R.attr
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.R.attr.button
import android.view.View

import android.view.ViewGroup




class MainActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val textView = findViewById<TextView>(R.id.textView)
		val recieved = findViewById<TextView>(R.id.recieved)
		val sent = findViewById<TextView>(R.id.sent)
		val message = findViewById<EditText>(R.id.message)
		val send_btn = findViewById<Button>(R.id.send_btn)
		initFindServerButton(textView, recieved, sent, message, send_btn)
	}

	private fun initFindServerButton(textView: TextView, recieved: TextView, sent: TextView,
									 message: EditText, send_btn: Button) {
		val btn = findViewById<Button>(R.id.find_server_btn)
		btn.setOnClickListener {
			btn.visibility = View.GONE

			Client(textView, recieved, sent, message, send_btn).start()
		}
	}
}
