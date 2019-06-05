package com.example.the_movie_db_app.ui.serie.api

import com.example.the_movie_db_app.app.base.models.BaseResponse
import com.example.the_movie_db_app.ui.serie.models.SerieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Romel Boada on 05/06/19.
 */

interface SerieApi {

    @GET("tv/popular")
    fun getPopularSerie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<SerieResult>>

    @GET("tv/on_the_air")
    fun getOnTheAirSerie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<SerieResult>>

    @GET("tv/top_rated")
    fun getTopRatedSerie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String) :
            Observable<BaseResponse<SerieResult>>
}