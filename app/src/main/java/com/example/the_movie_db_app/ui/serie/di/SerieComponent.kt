package com.example.the_movie_db_app.ui.serie.di

import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.serie.SerieFragment
import dagger.Component

/**
 * Created by Romel Boada on 05/06/19.
 */

@SerieScope
@Component(dependencies = [AppComponent::class], modules = [SerieModule::class])
interface SerieComponent {
    fun inject(serieFragment: SerieFragment)
}