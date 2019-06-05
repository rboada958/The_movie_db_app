package com.example.the_movie_db_app.app.di

import com.example.the_movie_db_app.app.network.di.ApiModule
import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import com.example.the_movie_db_app.ui.serie.SerieApi
import dagger.Component

/**
 * Created by Romel Boada on 05/06/19.
 */

@AppScope
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun moviesApi() : MoviesApi

    fun serieApi() : SerieApi
}