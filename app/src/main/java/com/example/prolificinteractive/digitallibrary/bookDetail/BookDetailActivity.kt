package com.example.prolificinteractive.digitallibrary.bookDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
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

        val titleTextView = findViewById<TextView>(R.id.textView2)
        titleTextView.text = book.title
        titleTextView.textSize = 50.0F

        val authorTextView = findViewById<TextView>(R.id.textView3)
        authorTextView.text = book.author
        authorTextView.textSize = 24.0F

        val publisherTitleTextView = findViewById<TextView>(R.id.textView5)
        publisherTitleTextView.textSize = 20.0F

        val publisherTextView = findViewById<TextView>(R.id.textView6)
        publisherTextView.text = book.publisher ?: "None"
        publisherTextView.textSize = 16.0F

        val categoryTitleTextView = findViewById<TextView>(R.id.textView7)
        categoryTitleTextView.textSize = 20.0F

        val categoryTextView = findViewById<TextView>(R.id.textView8)
        categoryTextView.text = book.categories ?: "None"
        categoryTextView.textSize = 16.0F
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
