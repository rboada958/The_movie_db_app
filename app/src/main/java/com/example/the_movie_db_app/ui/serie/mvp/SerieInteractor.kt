package com.example.the_movie_db_app.ui.serie.mvp

import com.example.the_movie_db_app.ui.serie.api.SerieApi
import com.example.the_movie_db_app.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieInteractor(var serieApi: SerieApi) {

    fun getPopularSerie(page: String) =
        serieApi.getPopularSerie(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

    fun getTopRatedSerie(page: String) =
        serieApi.getTopRatedSerie(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

    fun getOnTheAirSerie(page: String) =
        serieApi.getOnTheAirSerie(Utils.API_KEY, Utils.LANGUAGE, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

}