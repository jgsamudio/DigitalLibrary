package com.example.prolificinteractive.digitallibrary.api

import com.example.prolificinteractive.digitallibrary.models.Book
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result

class BooksRepository(private val apiService: LibraryApiService) {

    fun booksRequest(): Observable<Result<Array<Book>>> {
        return apiService.booksRequest()
    }

}
