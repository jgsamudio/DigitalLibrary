package com.example.prolificinteractive.digitallibrary.dependencies

import android.app.Application
import android.content.Context
import com.example.prolificinteractive.digitallibrary.MainActivity
import com.example.prolificinteractive.digitallibrary.api.LibraryRepositoryProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}