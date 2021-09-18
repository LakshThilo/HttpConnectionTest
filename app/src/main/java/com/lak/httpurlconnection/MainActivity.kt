package com.lak.httpurlconnection

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.lak.httpurlconnection.model.local.BooksRoomDatabase
import com.lak.httpurlconnection.model.local.entities.FavouriteBook
import com.lak.httpurlconnection.model.remote.Network
import com.lak.httpurlconnection.view.BookSearchFragment

private const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)

   //     tvResult = findViewById(R.id.result)

//        Thread(Runnable {
//            tvResult.text =
//            Network.getBooks()
//        }).start()

 //       insertBook()

  //      readFromBook()

        supportFragmentManager.beginTransaction()
            .replace(R.id.search_fragment_container, BookSearchFragment())
            .commit()

    }
/*
    private fun readFromBook() {
        val dao = BooksRoomDatabase.newInstance(this).getDao()

        dao.getFavouriteBook().forEach {book ->
            Toast.makeText(this@MainActivity, "$book", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertBook() {
        val dao = BooksRoomDatabase.newInstance(this).getDao()

        dao.insertFavouriteBook(
            FavouriteBook(
                image = "this is gonna be image URL",
                title = "this is book title",
                description = "this is book description",
                author = "author of the book"
            )
        )
    }*/


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