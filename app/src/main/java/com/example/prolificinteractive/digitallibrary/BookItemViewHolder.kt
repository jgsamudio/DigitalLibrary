package com.example.prolificinteractive.digitallibrary

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.book_item.view.*

class BookItemViewHolder(bookItemView: View) : RecyclerView.ViewHolder(bookItemView) {

    private var titleView: TextView = bookItemView.book_item_title
    private var authorView: TextView = bookItemView.book_item_author

    fun bind(book: BookItemViewModel) {
        with(itemView) {
            titleView.text = book.title
            titleView.textSize = 24.0F
            authorView.text = "By: Jonathan Samudio"
        }
    }

}