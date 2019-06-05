package com.example.the_movie_db_app.ui.movies.api

import com.example.the_movie_db_app.app.base.models.BaseResponse
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Romel Boada on 05/06/19.
 */

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<MovieResult>>

    @GET("movie/upcoming")
    fun getUpComingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<MovieResult>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<MovieResult>>
}