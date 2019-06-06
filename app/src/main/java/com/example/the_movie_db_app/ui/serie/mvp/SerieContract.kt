package com.example.the_movie_db_app.ui.serie.mvp

import com.example.the_movie_db_app.app.base.mvp.BaseContract
import com.example.the_movie_db_app.ui.serie.models.SerieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

interface SerieContract {

    interface Presenter: BaseContract.ServicePresenter {
        fun getPopularSerie(api_key: String, language: String, page: String)
        fun getTopRatedSerie(api_key: String, language: String, page: String)
        fun getOnTheAirSerie(api_key: String, language: String, page: String)
    }

    interface View: BaseContract.View {
        fun getPopularSerieSuccess(result: MutableList<SerieResult>?)
        fun getTopRatedSerieSuccess(result: MutableList<SerieResult>?)
        fun getOnTheAirSerieSuccess(result: MutableList<SerieResult>?)
    }
}