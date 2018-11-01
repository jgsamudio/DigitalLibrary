package com.example.prolificinteractive.digitallibrary.dependencies

import com.example.prolificinteractive.digitallibrary.addBook.AddBookViewModel
import com.example.prolificinteractive.digitallibrary.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(target: MainActivity)

}

@Singleton
@Component(modules = [ApiModule::class])
interface ViewModelInjector {

    fun inject(target: AddBookViewModel)
}