package com.example.the_movie_db_app.ui.movies.mvp

import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesInteractor(var moviesApi: MoviesApi) {

    fun getPopularMovies(api_key: String, language: String, page: String) =
        moviesApi.getPopularMovies(api_key, language, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!
}