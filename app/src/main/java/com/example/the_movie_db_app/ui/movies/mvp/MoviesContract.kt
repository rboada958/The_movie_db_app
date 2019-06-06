package com.example.the_movie_db_app.ui.movies.mvp

import com.example.the_movie_db_app.app.base.mvp.BaseContract
import com.example.the_movie_db_app.ui.movies.models.MovieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

interface MoviesContract {

    interface Presenter: BaseContract.ServicePresenter {
        fun getPopularMovies(page: String)
        fun getUpComingMovies(page: String)
        fun getTopRatedMovies(page: String)
    }



    interface View: BaseContract.View {
        fun getPopularMoviesSuccess(result: MutableList<MovieResult>?)
        fun getUpComingMoviesSuccess(result: MutableList<MovieResult>?)
        fun getTopRatedMoviesSuccess(result: MutableList<MovieResult>?)
    }
}