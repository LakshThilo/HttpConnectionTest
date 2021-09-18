package com.lak.httpurlconnection.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.lak.httpurlconnection.databinding.SingleBookResponseBinding
import com.lak.httpurlconnection.model.BookResponse
import com.lak.httpurlconnection.model.VolumeInfoDescription

class BookAdapter(private val bookData: BookResponse, private val callback: (Int) -> Unit) :RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        return BookViewHolder(
            SingleBookResponseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.onBind(bookData.items[position].volumeInfo, callback)
    }

    override fun getItemCount() = bookData.items.size


    class BookViewHolder(private val binding: SingleBookResponseBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(dataItem: VolumeInfoDescription,
        callback: (Int) -> Unit){
            binding.bookTitle.text = dataItem.title
            binding.bookAuthors.text = dataItem.authors.toString()
            binding.root.setOnClickListener { callback(adapterPosition) }
        }
    }

}