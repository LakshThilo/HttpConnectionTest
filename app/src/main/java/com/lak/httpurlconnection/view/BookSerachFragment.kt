package com.lak.httpurlconnection.view

import android.content.Context
import androidx.fragment.app.Fragment
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.presenter.BookSearchPresenter
import com.lak.httpurlconnection.utility.navigateResultFragment
import com.lak.httpurlconnection.view.contract.IBookResultFragment
import com.lak.httpurlconnection.view.contract.IBookSearchFragment

class BookSearchFragment : Fragment(), IBookSearchFragment{


    private lateinit var presenter : BookSearchPresenter

    override fun bindPresenter() {
        if(::presenter.isInitialized)
        presenter.onBind(this)
        else {
            presenter = BookSearchPresenter()
            presenter.onBind(this)
        }

    }

    override fun onStart() {
        super.onStart()
        sendRequestToPresenter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bindPresenter()
    }

    override fun unBindPresenter() {
        presenter.unBind()

    }

    override fun sendRequestToPresenter() {

        presenter.sendRequest(getInputBook())
    }

    private fun getInputBook(): String {

        return  "the lord of the ring"
    }

    override fun navigateResultFragment(inputBook: String) {

        activity?.navigateResultFragment(inputBook)
    }

}