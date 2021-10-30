package inft2501.leksjon_06.tjener

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(private val textView: TextView, private val recieved: TextView,
			 private val sent: TextView, private val message: EditText,
			 private val send_btn: Button, private val PORT: Int = 12345) {

	private var ui: String? = ""
		set(str) {
			MainScope().launch { textView.text = str }
			field = str
		}

	fun start() {
		CoroutineScope(Dispatchers.IO).launch {

			try {
				ui = "Starter Tjener ..."
				ServerSocket(PORT).use { serverSocket: ServerSocket ->
					ui =
						"ServerSocket opprettet, venter på at en klient kobler seg til...."

					while (true) {
						val clientSocket: Socket = serverSocket.accept()

						ClientHandler(clientSocket, textView, recieved, sent, message, send_btn).start()
					}
				}
			} catch (e: IOException) {
				e.printStackTrace()
				ui = e.message
			}
		}
	}
}
