package com.example.prolificinteractive.digitallibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.prolificinteractive.digitallibrary.api.LibraryRepositoryProvider
import com.example.prolificinteractive.digitallibrary.application.DigitalLibraryApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var libraryRepositoryProvider: LibraryRepositoryProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as DigitalLibraryApplication).libraryComponent.inject(this)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        setAddBookListener()

        val adapter = LibraryViewAdapter(arrayOf())
        recyclerView.adapter = adapter

        loadLibrary()
    }

    private fun setAddBookListener() {
        val floatingActionButton: View = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener { _ ->
            val intent = Intent(this, AddBookActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "HelloWorld")
            }
            startActivity(intent)
        }
    }

    private fun loadLibrary() {
        val repository = libraryRepositoryProvider.provideBooksRepository()
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