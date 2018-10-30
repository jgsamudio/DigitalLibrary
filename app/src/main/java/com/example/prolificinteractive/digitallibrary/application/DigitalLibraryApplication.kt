package com.example.prolificinteractive.digitallibrary.application

import android.app.Application
import com.example.prolificinteractive.digitallibrary.dependencies.AppComponent
import com.example.prolificinteractive.digitallibrary.dependencies.AppModule
import com.example.prolificinteractive.digitallibrary.dependencies.DaggerAppComponent

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