package com.lak.httpurlconnection.utility

import androidx.fragment.app.FragmentActivity
import com.lak.httpurlconnection.R
import com.lak.httpurlconnection.view.BookResultFragment

fun FragmentActivity.navigateResultFragment(bookInput: String){

    supportFragmentManager.beginTransaction()
        .replace(R.id.search_fragment_container, BookResultFragment.newInstance(bookInput))
        .commit()
}