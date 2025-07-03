package com.example.masjid_annur.adapters

import android.content.Context
import android.os.AsyncTask
import android.util.Log // Don't forget this import for Log.e
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ClientAsynkTask constructor(private val nContext: Context, private val postExecuteListener: OnPostExecuteListener):
    AsyncTask<String, Void, String?>(){
        val CONNECTION_TIMEOUT_MILLISECONDS = 6000// CHANGE THIS: Return type should be String?

    interface OnPostExecuteListener {
        fun onPostExecute(result: String?) // CHANGE THIS: Parameter should be String?
    }

    override fun onPostExecute(result: String?) { // CHANGE THIS: Parameter should be String?
        super.onPostExecute(result)
        postExecuteListener.onPostExecute(result)
    }

    override fun doInBackground(vararg urls: String?): String? { // CHANGE THIS: Return type should be String?
        var urlConnection: HttpURLConnection? = null

        try {
            val url = URL(urls[0])

            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connectTimeout = CONNECTION_TIMEOUT_MILLISECONDS
            urlConnection.readTimeout = CONNECTION_TIMEOUT_MILLISECONDS

            val responseCode = urlConnection.responseCode // Add this for proper HTTP response checking
            if (responseCode == HttpURLConnection.HTTP_OK) { // Check if the response is successful
                val inStream: InputStream = urlConnection.inputStream
                return streamToString(inStream)
            } else {
                Log.e("ClientAsynkTask", "HTTP Error: $responseCode - ${urlConnection.responseMessage}")
                return null // Return null on HTTP error
            }

        } catch (ex: Exception) {
            ex.printStackTrace() // Print stack trace for debugging
            Log.e("ClientAsynkTask", "Network error: ${ex.message}") // Log the specific error
            return null // Return null on any exception/network error
        } finally {
            urlConnection?.disconnect() // Use safe call operator for disconnect
        }
    }

    fun streamToString(inputStream: InputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?

        try {
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return stringBuilder.toString()
    }
}