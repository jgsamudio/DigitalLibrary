package com.example.prolificinteractive.digitallibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.prolificinteractive.digitallibrary.api.LibraryRepositoryProvider
import com.example.prolificinteractive.digitallibrary.models.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        setAddBookListener()
        loadLibrary()
    }

    private fun setAddBookListener() {
        val floatingActionButton: View = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener { _ ->
            // Route to add book activity
        }
    }

    private fun loadLibrary() {
        val repository = LibraryRepositoryProvider.provideBooksRepository()
        repository.booksRequest()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                val books = result.response()?.body()
                if (books != null) {
                    val adapter = LibraryViewAdapter(books)
                    recyclerView.adapter = adapter
                }
            }, { error ->
                error.printStackTrace()
            })
    }
}