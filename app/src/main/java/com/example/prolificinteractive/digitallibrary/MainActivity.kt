package com.example.prolificinteractive.digitallibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.prolificinteractive.digitallibrary.addBook.AddBookActivity
import com.example.prolificinteractive.digitallibrary.api.LibraryApiServiceProvider
import com.example.prolificinteractive.digitallibrary.application.DigitalLibraryApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var libraryApiServiceProvider: LibraryApiServiceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as DigitalLibraryApplication).libraryComponent.inject(this)
        setupActivity()
    }

    private fun setupActivity() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LibraryViewAdapter(arrayOf())
        setAddBookListener()
        loadLibrary()
        setupSwipeRefreshLayout()
    }

    private fun setAddBookListener() {
        val floatingActionButton: View = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "HelloWorld")
            }
            startActivity(intent)
        }
    }

    private fun setupSwipeRefreshLayout() {
        swipe_refresh_layout.setColorSchemeColors(ContextCompat.getColor(baseContext, R.color.colorPrimary))
        swipe_refresh_layout.setOnRefreshListener {
            loadLibrary()
        }
    }

    private fun loadLibrary() {
        val repository = libraryApiServiceProvider.apiService
        repository.booksRequest()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                val books = result.response()?.body()
                if (books != null) {
                    val adapter = LibraryViewAdapter(books)
                    recyclerView.adapter = adapter
                }
                swipe_refresh_layout.isRefreshing = false
            }, { error ->
                error.printStackTrace()
                swipe_refresh_layout.isRefreshing = false
            })
    }
}