package com.example.prolificinteractive.digitallibrary.api

import com.example.prolificinteractive.digitallibrary.BookItemViewModel

/**
 * This class, and the module as well, is a temporary solution till storage layer is implemented,
 * its dependency with domain should go out
 */
object DataSource {

    @Suppress("MagicNumber")
    fun getItems(): MutableList<BookItemViewModel> {
        val items: MutableList<BookItemViewModel> = mutableListOf()

        (1..100).forEachIndexed { index, i ->
            val item = BookItemViewModel(index, "My Cool Book " + index)
            items.add(item)
        }

        return items
    }
}