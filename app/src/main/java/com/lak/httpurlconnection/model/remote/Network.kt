package com.lak.httpurlconnection.model.remote

import android.net.Uri
import android.util.Log
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.model.ItemDescription
import com.lak.httpurlconnection.model.VolumeInfoDescription
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Network {

    companion object {

        private const val TAG = "Network"
        private const val BASE_URL = "https://www.googleapis.com/"
        private const val END_POINT = "/books/v1/volumes?"
        private const val QUERY_PARAM = "q"
        private const val MAX_RESULTS = "maxResults"
        private const val PRINT_TYPE = "printType"

        private var builtURI: Uri = Uri.parse("$BASE_URL$END_POINT").buildUpon()
            .appendQueryParameter(QUERY_PARAM, "pride+prejudice")
            .appendQueryParameter(MAX_RESULTS, "10")
            .appendQueryParameter(PRINT_TYPE, "books")
            .build()
        var requestURL: URL = URL(builtURI.toString())

        fun getBooks() : String? {

            val connection = requestURL.openConnection() as HttpURLConnection
            connection.readTimeout = 10 * 1000
            connection.connectTimeout = 10 * 1000
            connection.requestMethod = "GET"
            connection.doInput = true
            //connection.doOutput = true will use when we going to use PUT or POST

            connection.connect()

            val inputStream = connection.inputStream
            val responseCode = connection.responseCode

            val stringResponse = parseInputStream(inputStream)

            Log.d(TAG, "getBooks: $stringResponse")

            return stringResponse ?: ""
        }

        private fun parseInputStream(inputStream: InputStream): String? {

            val stringBuilder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var line = reader.readLine()
            while (line != null){
                stringBuilder.append("$line\n") //String interplomation
                line = reader.readLine()
            }
            return if(stringBuilder.isEmpty()) null else stringBuilder.toString() //if
        }

        fun bookResponseFactory() : BookResponse{

            val response = getBooks()
            val jsonRoot = JSONObject(response)

            val  jsonItems = jsonRoot.getJSONArray("items")
            //[{},{},{}] what we get from JSON

            val collVolumeInfo = mutableListOf<ItemDescription>()

            for(i in 0 until jsonItems.length()) {
                val jsonItemDesc:JSONObject =  jsonItems[i] as JSONObject
                val jsonVolumeInfo = jsonItemDesc.getJSONObject("volumeInfo")
                val title = jsonVolumeInfo.getString("title")
                val authors = jsonItemDesc.get("authors") as List<String>
                val jsonIndustry = jsonVolumeInfo.getJSONArray("industryIdentifiers")

                val volumeInfoDescription = VolumeInfoDescription( title, authors, null)

                val itemDescription = ItemDescription(volumeInfoDescription)

                collVolumeInfo.add(itemDescription)
            }

        return BookResponse(collVolumeInfo)
        }
    }

}