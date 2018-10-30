package com.example.prolificinteractive.digitallibrary

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText

class AddBookActivity : AppCompatActivity() {

    private val viewModel = AddBookViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        setupActionBar()
        setupEditText()
        setupAddBookButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.noChangesMade()) {
            onBackPressed()
        } else {
            displayDiscardAlertDialog()
        }
        return false
    }

    private fun displayDiscardAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.discard_information)
        builder.setMessage("Are you sure you want to discard information?")
        builder.setPositiveButton("Discard") { _, _ ->
            onBackPressed()
        }
        builder.setNegativeButton("Cancel", null)
        builder.create()?.show()
    }

    private fun setupEditText() {
        val titleEditText: EditText = findViewById(R.id.editText1)
        titleEditText.onChange { viewModel.title = it }

        val authorEditText: EditText = findViewById(R.id.editText2)
        authorEditText.onChange { viewModel.author = it }

        val publisherEditText: EditText = findViewById(R.id.editText3)
        publisherEditText.onChange { viewModel.publisher = it }

        val categoryEditText: EditText = findViewById(R.id.editText4)
        categoryEditText.onChange { viewModel.categories = it }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupAddBookButton() {
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // Check if the view's fields are valid
            if (viewModel.fieldsValid()) {
                // Make network call
                // Close activity
            } else {
                val view = findViewById<View>(android.R.id.content)
                Snackbar.make(view, viewModel.fieldsErrorText(this.baseContext), Snackbar.LENGTH_LONG).show()
            }
        }
    }
}

class AddBookViewModel {

    var title: String = ""
    var author: String = ""
    var publisher: String = ""
    var categories: String = ""

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

}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { cb(s.toString()) }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}