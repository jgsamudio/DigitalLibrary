package com.example.prolificinteractive.digitallibrary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class LibraryViewAdapter(private val items: MutableList<BookItemViewModel>) : RecyclerView.Adapter<BookItemViewHolder>() {

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(parent.inflate(R.layout.book_item))
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
}