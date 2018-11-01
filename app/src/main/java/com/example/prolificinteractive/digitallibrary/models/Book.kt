package com.example.prolificinteractive.digitallibrary.models

data class Book (val title: String = "",
                 val author: String = "",
                 val id: Int,
                 val publisher: String?,
                 val url: String)


data class BookRequest(val title: String = "", val author: String = "")