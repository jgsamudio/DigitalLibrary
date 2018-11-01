package com.example.prolificinteractive.digitallibrary.bookDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.prolificinteractive.digitallibrary.R
import com.example.prolificinteractive.digitallibrary.models.Book

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_BOOK = "SELECTED_BOOK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setupActionBar()

        val book = intent.getSerializableExtra(SELECTED_BOOK) as Book
        title = book.title
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
