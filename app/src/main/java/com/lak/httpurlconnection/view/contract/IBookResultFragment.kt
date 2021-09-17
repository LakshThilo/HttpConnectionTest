package com.lak.httpurlconnection.view.contract

import com.lak.httpurlconnection.model.BookResponse

interface IBookResultFragment {

    //receive data

    fun onBind()

    fun unBind()

    fun displayData(dataSet: BookResponse)
}