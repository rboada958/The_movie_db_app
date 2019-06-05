package com.example.the_movie_db_app.ui.serie.mvp

import com.example.the_movie_db_app.ui.serie.api.SerieApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieInteractor(var serieApi: SerieApi) {

    fun getPopularSerie(api_key: String, language: String, page: String) =
        serieApi.getPopularSerie(api_key, language, page).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())!!

}