package com.example.prolificinteractive.digitallibrary.bookDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.example.prolificinteractive.digitallibrary.R
import com.example.prolificinteractive.digitallibrary.models.Book
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import kotlinx.android.synthetic.main.bottom_sheet.*


class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val SELECTED_BOOK = "SELECTED_BOOK"
    }

    private var viewModel = BookDetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)
        setupActionBar()

        val book = intent.getSerializableExtra(SELECTED_BOOK) as Book
        viewModel.book = book

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

        setupCheckoutBottomSheet()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        submitButton.dispose()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupCheckoutBottomSheet() {
        val checkoutTextView = findViewById<TextView>(R.id.textView)
        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        val checkoutEditText = findViewById<EditText>(R.id.editText)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        checkoutTextView.textSize = 16.0F
        checkoutTextView.text = getString(R.string.checkout).toUpperCase()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        checkoutTextView.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                checkoutEditText.visibility = View.INVISIBLE
            } else if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                checkoutEditText.visibility = View.VISIBLE
            }
        }

        submitButton.setOnClickListener {
            val name = checkoutEditText.text.toString()
            if (viewModel.isValidBorrowerName(name)) {
                submitButton.startAnimation()
                viewModel.checkoutBook(name) {
                    submitButton.stopAnimation()
                    // Close Bottom Sheet
                }
            } else {
                val view = findViewById<View>(android.R.id.content)
                Snackbar.make(view, "Please enter a valid name!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
