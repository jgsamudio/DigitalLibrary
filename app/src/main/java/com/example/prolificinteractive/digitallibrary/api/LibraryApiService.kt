package com.example.prolificinteractive.digitallibrary.api

import com.example.prolificinteractive.digitallibrary.models.Book
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface LibraryApiService {

    @GET("books")
    fun booksRequest(): Observable<Result<Array<Book>>>

    /**
     * Companion object to create the Library API Service
     */
    companion object Factory {
        fun create(): LibraryApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://prolific-interview.herokuapp.com/5b9676e767a8480009af4daa/")
                .build()

            return retrofit.create(LibraryApiService::class.java)
        }
    }
}