package com.example.prolificinteractive.digitallibrary.api

object LibraryRepositoryProvider {

    private val apiService = LibraryApiService.create()

    fun provideBooksRepository(): BooksRepository {
        return BooksRepository(apiService)
    }
}