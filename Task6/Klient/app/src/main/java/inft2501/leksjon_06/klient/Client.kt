package inft2501.leksjon_06.klient

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
	private val textView: TextView,
	private val recieved: TextView,
	private val sent: TextView,
	private val message: EditText,
	private val send_btn: Button,
	private val SERVER_IP: String = "10.12.4.211", //Må endres til egen ip
	private val SERVER_PORT: Int = 12345) {

	private var ui: String? = ""
		set(str) {
			MainScope().launch { textView.text = str }
			field = str
		}


	fun start() {
		CoroutineScope(Dispatchers.IO).launch {
			ui = "Kobler til tjener..."
			try {
				Socket(SERVER_IP, SERVER_PORT).use { socket: Socket ->
					ui = "Koblet til tjener"

					initSendMessageButton(socket)

					while (true){
						readFromServer(socket)
						delay(1000)
					}

				}
			} catch (e: IOException) {
				e.printStackTrace()
				ui = e.message
			}
		}
	}

	private fun initSendMessageButton(socket: Socket) {
		send_btn.setOnClickListener {
			sendToServer(socket, message.text.toString())
			message.text.clear()
		}
	}

	private fun readFromServer(socket: Socket) {
		val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
		val message = reader.readLine()
		MainScope().launch {
			recieved.text = "$message"
		}
	}

	private fun sendToServer(socket: Socket, message: String) {
		CoroutineScope(Dispatchers.IO).launch {
			val writer = PrintWriter(socket.getOutputStream(), true)
			writer.println(message)
		}
		if (message != "") {
			MainScope().launch {
				sent.text = "$message"
			}
		}
	}
}
