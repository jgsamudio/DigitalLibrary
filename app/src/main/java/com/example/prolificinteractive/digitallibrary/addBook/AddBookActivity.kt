package com.example.prolificinteractive.digitallibrary.addBook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton
import com.example.prolificinteractive.digitallibrary.R
import com.example.prolificinteractive.digitallibrary.extensions.onChange

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
        builder.setMessage(getString(R.string.discard_information_description))
        builder.setPositiveButton(getString(R.string.discard)) { _, _ ->
            onBackPressed()
        }
        builder.setNegativeButton(getString(R.string.cancel), null)
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
        val button = findViewById<CircularProgressButton>(R.id.button)
        button.setOnClickListener {
            if (viewModel.fieldsValid()) {
                button.startAnimation()
                viewModel.addBookToLibrary { success ->
                    if (success) {
                        onBackPressed()
                    }
                }
            } else {
                val view = findViewById<View>(android.R.id.content)
                Snackbar.make(view, viewModel.fieldsErrorText(this.baseContext), Snackbar.LENGTH_LONG).show()
            }
        }
    }
}