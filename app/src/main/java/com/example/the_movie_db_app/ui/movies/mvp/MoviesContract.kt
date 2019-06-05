package com.example.the_movie_db_app.ui.movies.mvp

import com.example.the_movie_db_app.app.base.mvp.BaseContract
import com.example.the_movie_db_app.ui.movies.models.MovieResult

interface MoviesContract {

    interface Presenter: BaseContract.ServicePresenter {
        fun getPopularMovies(api_key: String, language: String, page: String)
    }

    interface View: BaseContract.View {
        fun getPopularMoviesSuccess(result: MutableList<MovieResult?>?)
    }
}