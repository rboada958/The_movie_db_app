package com.example.the_movie_db_app.ui.movies.di

import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import com.example.the_movie_db_app.ui.movies.mvp.MoviesContract
import com.example.the_movie_db_app.ui.movies.mvp.MoviesInteractor
import com.example.the_movie_db_app.ui.movies.mvp.MoviesPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module
class MoviesModule(var view: MoviesContract.View) {

    @Provides
    @MoviesScope
    fun providesView() : MoviesContract.View {
        return view
    }

    @Provides
    @MoviesScope
    fun providesInteractor(moviesApi: MoviesApi) : MoviesInteractor {
        return MoviesInteractor(moviesApi)
    }

    @Provides
    @MoviesScope
    fun providerPresenter(interactor: MoviesInteractor, view: MoviesContract.View) : MoviesPresenter {
        return MoviesPresenter(interactor, view)
    }
}