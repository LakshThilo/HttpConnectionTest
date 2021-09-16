package com.lak.httpurlconnection

import android.app.Application
import com.lak.httpurlconnection.model.local.BookDao
import com.lak.httpurlconnection.model.local.BooksRoomDatabase

class BookApplication :Application() {


    /**
     * Init any library before MainActivity*/
    override fun onCreate() {
        super.onCreate()
        bookDao = BooksRoomDatabase.newInstance(this).getDao()
    }

    companion object {

        lateinit var bookDao: BookDao
    }
}