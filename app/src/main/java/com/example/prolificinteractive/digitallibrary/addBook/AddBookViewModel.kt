package com.example.prolificinteractive.digitallibrary.addBook

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.prolificinteractive.digitallibrary.R
import com.example.prolificinteractive.digitallibrary.api.LibraryApiServiceProvider
import com.example.prolificinteractive.digitallibrary.application.BaseViewModel
import com.example.prolificinteractive.digitallibrary.models.BookRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddBookViewModel: BaseViewModel() {

    @Inject
    lateinit var libraryApiServiceProvider: LibraryApiServiceProvider

    var title: String = ""
    var author: String = ""
    var publisher: String = ""
    var categories: String = ""

    var bookAdded: MutableLiveData<Boolean> = MutableLiveData()

    fun noChangesMade(): Boolean {
        return title.isEmpty() && author.isEmpty() && publisher.isEmpty() && categories.isEmpty()
    }

    fun fieldsValid(): Boolean {
        if (!title.isEmpty() && !author.isEmpty()) {
            return true
        }
        return false
    }

    fun fieldsErrorText(context: Context): String {
        return when {
            title.isEmpty() -> context.getString(R.string.valid_title_error)
            author.isEmpty() -> context.getString(R.string.valid_author_error)
            else -> ""
        }
    }

    fun addBookToLibrary() {
        println(author)
        println(title)
        libraryApiServiceProvider.apiService
            .addBookRequest(BookRequest(title, author))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                bookAdded.value = true
            }, { error ->
                error.printStackTrace()
                bookAdded.value = false
            })
    }
}