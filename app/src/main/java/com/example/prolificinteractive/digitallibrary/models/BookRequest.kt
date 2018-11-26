package com.example.prolificinteractive.digitallibrary.models

data class BookRequest(val title: String = "",
                       val author: String = "",
                       val categories: String? = null,
                       val publisher: String? = null)

data class CheckoutRequest(val lastCheckedOutBy: String)