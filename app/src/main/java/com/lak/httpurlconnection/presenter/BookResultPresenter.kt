package com.lak.httpurlconnection.presenter

import android.util.Log
import com.lak.httpurlconnection.model.remote.BookApi
import com.lak.httpurlconnection.view.contract.IBookResultFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class BookResultPresenter {

    private var view: IBookResultFragment? = null
    private val TAG = "TBook Result Present"


    fun onBind(bookResultFragment: IBookResultFragment) {

        view = bookResultFragment
    }

    fun unBind() {
       view = null
    }

    fun searchBook(bookName: String) {

        BookApi.getApi().getBooksByName(bookName=bookName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                if(it.isSuccessful)
                    it.body()?.let { data ->view?.displayData(data) } // proper statement
                    //view?.displayData(it.body()!!) // !! if null trigger NPE
            },{
                Log.e(TAG, "searchBook: ",it )
            })

    }
}