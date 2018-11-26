package com.example.prolificinteractive.digitallibrary.application

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.prolificinteractive.digitallibrary.addBook.AddBookViewModel
import com.example.prolificinteractive.digitallibrary.bookDetail.BookDetailViewModel
import com.example.prolificinteractive.digitallibrary.dependencies.*

@Suppress("DEPRECATION")
class DigitalLibraryApplication: Application() {

    lateinit var libraryComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        libraryComponent = initDagger(this)
    }

    private fun initDagger(app: DigitalLibraryApplication): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(app)).build()
    }
}

open class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .apiModule(ApiModule())
        .build()

    init {
        when(this) {
            is AddBookViewModel -> injector.inject(this)
            is BookDetailViewModel -> injector.inject(this)
        }
    }
}