package com.example.the_movie_db_app.ui.movies.di

import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.movies.MovieFragment
import dagger.Component

@MoviesScope
@Component(dependencies = [AppComponent::class], modules = [MoviesModule::class])
interface MoviesComponent {
    fun inject(movieFragment: MovieFragment)
}