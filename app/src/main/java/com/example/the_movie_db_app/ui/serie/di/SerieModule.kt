package com.example.the_movie_db_app.ui.serie.di

import com.example.the_movie_db_app.ui.serie.api.SerieApi
import com.example.the_movie_db_app.ui.serie.mvp.SerieContract
import com.example.the_movie_db_app.ui.serie.mvp.SerieInteractor
import com.example.the_movie_db_app.ui.serie.mvp.SeriePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module
class SerieModule(var view: SerieContract.View) {

    @Provides
    @SerieScope
    fun providesView() : SerieContract.View {
        return view
    }

    @Provides
    @SerieScope
    fun providesInteractor(serieApi: SerieApi) : SerieInteractor {
        return SerieInteractor(serieApi)
    }

    @Provides
    @SerieScope
    fun providerPresenter(interactor: SerieInteractor, view: SerieContract.View) : SeriePresenter {
        return SeriePresenter(interactor, view)
    }
}