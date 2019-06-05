package com.example.the_movie_db_app.ui.movies.di

import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import com.example.the_movie_db_app.ui.movies.mvp.MoviesContract
import com.example.the_movie_db_app.ui.movies.mvp.MoviesInteractor
import com.example.the_movie_db_app.ui.movies.mvp.MoviesPresenter
import dagger.Module
import dagger.Provides

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
    fun providerPresenter(interactor: MoviesInteractor) : MoviesPresenter {
        return MoviesPresenter(interactor)
    }
}