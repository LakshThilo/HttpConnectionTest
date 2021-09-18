package com.lak.httpurlconnection.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lak.httpurlconnection.databinding.DisplayBooksSearchBinding
import com.lak.httpurlconnection.databinding.SingleBookResponseBinding
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.presenter.BookResultPresenter
import com.lak.httpurlconnection.view.adapter.BookAdapter
import com.lak.httpurlconnection.view.contract.IBookResultFragment
import com.lak.httpurlconnection.viewmodel.BookViewModel

// Implementation of View Model and LiveData Connection

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

   // private lateinit var presenter : BookResultPresenter
    private val viewModel: BookViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return BookViewModel() as T
                }
            }
        ).get(BookViewModel::class.java)
       //[BookViewModel::class.java] // same as above
   }

    private lateinit var binding: DisplayBooksSearchBinding


    override fun onBind() {
       // presenter.onBind(this)
    }

    override fun unBind() {
       // presenter.unBind()
    }

    override fun displayData(dataSet: BookResponse) {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         super.onCreateView(inflater, container, savedInstanceState)
         binding = DisplayBooksSearchBinding.inflate(
             inflater,
             container,
             false
         )

        arguments?.getString(EXTRA_BOOK_NAME)?.let {
        //   presenter.searchBook(it)
            viewModel.searchBookName(it)
        }

        viewModel.resultData.observe(viewLifecycleOwner,
        object: Observer<BookResponse>{
            override fun onChanged(t: BookResponse?) {
                // this is going to be received in Main Thread
                initViews(t)
            }
        })

        return binding.root
    }

    private fun initViews(t: BookResponse?) {
        t?.let { it ->
            binding.bookList.layoutManager = LinearLayoutManager(activity)
            binding.bookList.adapter = BookAdapter(it){
                // trailing lambda -> callback lambda - another option use method reference or creating function ur self
                // when user lick on button

                Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
            }
        }

    }
}