package com.example.prolificinteractive.digitallibrary.bookDetail

import com.example.prolificinteractive.digitallibrary.api.LibraryApiServiceProvider
import com.example.prolificinteractive.digitallibrary.application.BaseViewModel
import com.example.prolificinteractive.digitallibrary.models.Book
import com.example.prolificinteractive.digitallibrary.models.CheckoutRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookDetailViewModel: BaseViewModel() {

    @Inject lateinit var libraryApiServiceProvider: LibraryApiServiceProvider

    var book: Book? = null

    fun isValidBorrowerName(name: String): Boolean {
        return !name.isEmpty()
    }

    fun checkoutBook(borrowerName: String, completion: (Boolean) -> Unit) {
        if (book != null) {
            libraryApiServiceProvider.apiService
                .checkoutBookRequest(book!!.id, CheckoutRequest(borrowerName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    completion(true)
                }, { error ->
                    error.printStackTrace()
                    completion(false)
                })
        }
    }
}
