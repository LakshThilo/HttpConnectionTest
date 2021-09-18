package com.lak.httpurlconnection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.model.remote.BookApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * A container of Data.  It will survive configuration changes.
 *
 * */
class BookViewModel : ViewModel(){

    // {_variable} means backing field, will be used in this class for value reference
    private val _resultData = MutableLiveData<BookResponse>()

    val resultData: LiveData<BookResponse> get() = _resultData // basically get the _resultData and save in result data

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse : LiveData<String> get() = _errorResponse

    private val bookApi: BookApi by lazy{
        BookApi.getApi()

    }

    fun searchBookName(bookName: String){

        bookApi.getBooksByName(bookName)
            .subscribeOn(Schedulers.io()) //subscribe on Worker Thread
            .observeOn(AndroidSchedulers.mainThread()) // Observe on Main Thread
            .subscribe({
                // on Success / onNext       - Here in Main Thread
                       if(it.isSuccessful) // response 200-300
                           _resultData.value = it.body()!! //update result data with response body
            },{
                // on Error
                _errorResponse.value = it.message
            })
    }
}
