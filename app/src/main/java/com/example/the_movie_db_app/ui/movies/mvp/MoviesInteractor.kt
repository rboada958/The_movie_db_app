package com.example.the_movie_db_app.ui.movies.mvp

import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import com.example.the_movie_db_app.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Romel Boada on 05/06/19.
 */

class MoviesInteractor(var moviesApi: MoviesApi) {

    fun getPopularMovies(page: String) =
        moviesApi.getPopularMovies(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

    fun getUpComingMovies(page: String) =
        moviesApi.getUpComingMovies(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

    fun getTopRatedMovies(page: String) =
        moviesApi.getTopRatedMovies(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!
}