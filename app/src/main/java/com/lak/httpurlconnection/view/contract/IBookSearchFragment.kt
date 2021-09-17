package com.lak.httpurlconnection.view.contract

interface IBookSearchFragment {

    //input a book name
    // send request to Presenter

    fun unBindPresenter()

    fun bindPresenter()

    fun sendRequestToPresenter()

    fun navigateResultFragment(inputBook: String) {

    }


}