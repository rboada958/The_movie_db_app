package com.example.the_movie_db_app.ui.movies.di

import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.movies.MovieFragment
import dagger.Component

/**
 * Created by Romel Boada on 05/06/19.
 */

@MoviesScope
@Component(dependencies = [AppComponent::class], modules = [MoviesModule::class])
interface MoviesComponent {
    fun inject(movieFragment: MovieFragment)
}