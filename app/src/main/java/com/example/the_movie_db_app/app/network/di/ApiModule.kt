package com.example.the_movie_db_app.app.network.di

import com.example.the_movie_db_app.app.di.AppScope
import com.example.the_movie_db_app.ui.movies.api.MoviesApi
import com.example.the_movie_db_app.ui.serie.api.SerieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module(includes = [RetrofitModule::class])
class ApiModule {

    @Provides
    @AppScope
    fun provideMoviesApi(retrofit: Retrofit) : MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @AppScope
    fun provideSerieApi(retrofit: Retrofit) : SerieApi {
        return retrofit.create(SerieApi::class.java)
    }
}