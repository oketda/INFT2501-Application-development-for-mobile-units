package com.example.task5

import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.*

enum class HTTP { GET, POST }

const val ENCODING = "UTF-8"

class HttpWrapper(private val URL: String) {

    init {
        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))
    }

    private fun openConnection(url: String): URLConnection {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.setRequestProperty("Accept-Charset", ENCODING)
        return connection
    }

    fun get(parameterList: Map<String, String>): String {
        Log.e("INFT", URL + encodeParameters(parameterList))
        val connection = openConnection(URL + encodeParameters(parameterList))
        connection.inputStream.use { response ->
            Log.e("INFT", "kommer hit")
            return readResponseBody(response, getCharSet(connection))
        }
    }

    fun post(parameterList: Map<String, String>): String {
        val connection = openConnection(URL)

        // Tell connection we are going to send data
        connection.doOutput = true

        //tell server what type of data we're sending, alternatives are application/json etc.
        val outputType = "application/x-www-form-urlencoded; charset=$ENCODING"
        connection.setRequestProperty("Content-Type", outputType)

        connection.outputStream.use { outputStream ->
            val postString = encodeParameters(parameterList)
            outputStream.write(postString.toByteArray(charset(ENCODING)))
        }
        connection.inputStream.use { inputStream ->
            return readResponseBody(inputStream, getCharSet(connection))
        }
    }

    private fun encodeParameters(parameterList: Map<String, String>): String {
        var parameterString = "?"
        for ((key, value) in parameterList) {
            try {
                parameterString += URLEncoder.encode(key, ENCODING)
                parameterString += "="
                parameterString += URLEncoder.encode(value, ENCODING)
                parameterString += "&"
            } catch (e: UnsupportedEncodingException) {
                Log.e("encodeParameters()", e.toString())
            }
        }
        return parameterString
    }

    private fun readResponseBody(inputStream: InputStream, charset: String?): String {
        var body = ""
        try {
            BufferedReader(InputStreamReader(inputStream, charset)).use { bufferedReader ->
                var line: String?
                do {
                    line = bufferedReader.readLine()
                    body += "$line\n"
                } while (line != null)
            }
        } catch (e: Exception) {
            Log.e("readResponseBody()", e.toString())
            body += "******* Problem reading from server *******\n$e"
        }
        return body
    }

    private fun getCharSet(connection: URLConnection): String? {
        var charset: String? = ENCODING
        val contentType = connection.contentType
        val contentInfo = contentType.replace(" ", "").split(";").toTypedArray()
        for (param in contentInfo) {
            if (param.startsWith("charset=")) charset = param.split("=").toTypedArray()[1]
        }
        Log.i("getCharSet()", "contentType = $contentType")
        Log.i("getCharSet()", "Encoding/charset = $charset")
        return charset
    }
}