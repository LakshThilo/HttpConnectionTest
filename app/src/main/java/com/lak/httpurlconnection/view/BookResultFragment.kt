package com.lak.httpurlconnection.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lak.httpurlconnection.databinding.SingleBookResponseBinding
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.presenter.BookResultPresenter
import com.lak.httpurlconnection.view.contract.IBookResultFragment


class BookResultFragment :Fragment(), IBookResultFragment {

    companion object {
        private const val EXTRA_BOOK_NAME = " EXTRA_BOOK_NAME "
        fun newInstance(bookInput: String) =
            BookResultFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_BOOK_NAME, bookInput)
                }
            }

    }

    private lateinit var presenter : BookResultPresenter
    private lateinit var binding: SingleBookResponseBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = BookResultPresenter()
        onBind()
    }
    override fun onBind() {

        presenter.onBind(this)
    }

    override fun unBind() {

        presenter.unBind()
    }

    override fun displayData(dataSet: BookResponse) {

        Toast.makeText(activity, "$dataSet", Toast.LENGTH_SHORT).show()

        // if you get the all list displyed iterate the list
        binding.bookTitle.text = dataSet.items[0].volumeInfo.title
        binding.bookAuthors.text = dataSet.items[0].volumeInfo.authors.toString()

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         super.onCreateView(inflater, container, savedInstanceState)
         binding = SingleBookResponseBinding.inflate(
             inflater,
             container,
             false
         )

        arguments?.getString(EXTRA_BOOK_NAME)?.let {
            presenter.searchBook(it)
        }

        return binding.root
    }
}