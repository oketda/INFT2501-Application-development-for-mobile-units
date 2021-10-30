package inft2501.leksjon_06.tjener

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ClientHandler(private val clientSocket: Socket, private val textView: TextView,
                    private val recieved: TextView, private val sent: TextView,
                    private val message: EditText, private val send_btn: Button) {

    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulatoren med
     *
     * ```
     * ui = "noe"
     * ```
     */
    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Client connected.."

            initSendButton(clientSocket)

            while (true) {
                readFromClient(clientSocket)
                delay(1000)
            }
        }
    }

    private fun initSendButton(socket: Socket) {
        send_btn.setOnClickListener {
            sendToClient(socket, message.text.toString())
            message.text.clear()
        }
    }

    private fun readFromClient(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()
        if (message != "") {
            MainScope().launch {
                recieved.text = "$message"
            }
        }
    }

    private fun sendToClient(socket: Socket, message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val writer = PrintWriter(socket.getOutputStream(), true)
            writer.println(message)
        }
        MainScope().launch {
            sent.text = "$message"
        }
    }
}