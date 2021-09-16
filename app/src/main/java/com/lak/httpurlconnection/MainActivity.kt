package com.lak.httpurlconnection

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.lak.httpurlconnection.model.network.Network

private const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.result)

//        Thread(Runnable {
//            tvResult.text =
//            Network.getBooks()
//        }).start()


    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }
}

/**
 * Parameter in order
 * @param First defines the URL to execute a operation
 * @param Second define the measure of the Progress Update
 * @param Last define the returning value of the doInbackground
 * and the input from PostExecute
* */

class NetworkAsync(private val tvResult : TextView) :AsyncTask<String, Int, String>(){

    override fun doInBackground(vararg p0: String?): String? {

        return Network.getBooks()
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        tvResult.text = result
    }

}
/*
*
*   1. Add permissions to Android Manifest
    2. Check Network Connection
    3. Create Worker Thread
    4. Implement background task
        a. Create URI
        b. Make HTTP Connection
        c. Connect and GET Data
    5. Process results
        a. Parse Results
*
* */