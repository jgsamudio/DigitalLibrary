package com.example.prolificinteractive.digitallibrary.models

import java.io.Serializable

data class Book (val title: String = "",
                 val author: String = "",
                 val id: Int,
                 val publisher: String?,
                 val url: String): Serializable