package inft2501.leksjon_06.tjener

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val textView = findViewById<TextView>(R.id.textView)
		val recieved = findViewById<TextView>(R.id.recieved)
		val sent = findViewById<TextView>(R.id.sent)
		val message = findViewById<EditText>(R.id.message)
		val send_btn = findViewById<Button>(R.id.send_btn)
		initStartServerButton(textView, recieved, sent, message, send_btn)
	}

	private fun initStartServerButton(textView: TextView, recieved: TextView, sent: TextView,
									  message: EditText, send_btn: Button) {
		val btn = findViewById<Button>(R.id.start_server_btn)
		btn.setOnClickListener {
			btn.visibility = View.GONE

			Server(textView, recieved, sent, message, send_btn).start()
		}
	}
}
