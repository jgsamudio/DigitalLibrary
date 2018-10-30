package com.example.prolificinteractive.digitallibrary.api

class LibraryRepositoryProvider {

    private val apiService = LibraryApiService.create()

    fun provideBooksRepository(): BooksRepository {
        return BooksRepository(apiService)
    }
}