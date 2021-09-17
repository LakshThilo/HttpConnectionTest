package com.lak.httpurlconnection.model.remote

import com.lak.httpurlconnection.model.BookResponse
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*interface BookApi {
    @GET("books/v1/volumes")
     fun getBooksByName(
        @Query("q")
        bookName: String = "bible",
        @Query("maxResults")
        maxResult: Int = 5,
        @Query("printType")
        printType: String = "books"
    ): Observable<Response<BookResponse>>


    companion object{
        fun getApi() =
            Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BookApi::class.java)


        private fun createClient(): OkHttpClient {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder().addInterceptor(logging).build()
        }
    }
}*/

interface BookApi {

    //http://books.some.som/book/{id}/volumes  ?q=kdkdkd&maxRe=484884&print=kdkdkdkd

    @GET("books/v1/volumes")
    fun getBooksByName(
        @Query("q")
        bookName: String = "bible",
        @Query("maxResults")
        maxResults: Int = 5,
        @Query("printType")
        printType: String = "books"
    ): Observable<Response<BookResponse>>

    companion object{
        fun getApi() =
            Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BookApi::class.java)
    }
}