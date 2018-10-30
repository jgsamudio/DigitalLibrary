package com.example.prolificinteractive.digitallibrary.dependencies

import com.example.prolificinteractive.digitallibrary.api.LibraryRepositoryProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideBooksRepository(): LibraryRepositoryProvider = LibraryRepositoryProvider()
}