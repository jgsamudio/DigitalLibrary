package com.example.prolificinteractive.digitallibrary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prolificinteractive.digitallibrary.models.Book

class LibraryViewAdapter(private val items: Array<Book>,
                         private val itemClickListener: (Book) -> Unit) : RecyclerView.Adapter<BookItemViewHolder>() {

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(parent.inflate(R.layout.book_item))
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}