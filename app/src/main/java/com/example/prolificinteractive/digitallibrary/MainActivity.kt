package com.example.prolificinteractive.digitallibrary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.prolificinteractive.digitallibrary.addBook.AddBookActivity
import com.example.prolificinteractive.digitallibrary.api.LibraryApiServiceProvider
import com.example.prolificinteractive.digitallibrary.application.DigitalLibraryApplication
import com.example.prolificinteractive.digitallibrary.bookDetail.BookDetailActivity
import com.example.prolificinteractive.digitallibrary.models.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_BOOK_REQUEST_CODE = 1
        const val BOOK_DETAIL_REQUEST_CODE = 1

        init {
            System.loadLibrary("shared-library")
        }
    }

    @Inject
    lateinit var libraryApiServiceProvider: LibraryApiServiceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as DigitalLibraryApplication).libraryComponent.inject(this)
        setupActivity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadLibrary()
        } else {
            recyclerView.adapter.notifyDataSetChanged()
        }
    }

    private fun setupActivity() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = buildLibraryViewAdapter(arrayOf())
        setAddBookListener()
        setupSwipeRefreshLayout()
        loadLibrary()
    }

    private fun setAddBookListener() {
        val floatingActionButton: View = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE)
        }
    }

    private fun setupSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeColors(ContextCompat.getColor(baseContext, R.color.colorPrimary))
        swipe_refresh_layout.setOnRefreshListener {
            loadLibrary()
        }
    }

    private fun loadLibrary() {
        swipe_refresh_layout.isRefreshing = true
        val repository = libraryApiServiceProvider.apiService
        repository.booksRequest()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                val books = result.response()?.body()
                if (books != null) {
                    recyclerView.adapter = buildLibraryViewAdapter(books)
                }
                swipe_refresh_layout.isRefreshing = false
            }, { error ->
                error.printStackTrace()
                swipe_refresh_layout.isRefreshing = false
            })
    }

    private fun buildLibraryViewAdapter(books: Array<Book>): LibraryViewAdapter {
        return LibraryViewAdapter(books) { book ->
            val intent = Intent(this, BookDetailActivity::class.java).apply {
                putExtra(BookDetailActivity.SELECTED_BOOK, book)
            }
            startActivityForResult(intent, BOOK_DETAIL_REQUEST_CODE)
        }
    }
}