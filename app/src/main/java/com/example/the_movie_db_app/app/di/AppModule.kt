package com.example.the_movie_db_app.app.di

import android.content.Context
import android.content.res.Resources
import com.example.the_movie_db_app.app.App
import dagger.Module
import dagger.Provides

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module
class AppModule(val app: App) {

    @Provides
    @AppScope
    fun provideApp(): App {
        return app
    }

    @Provides
    @AppScope
    fun provideResources(): Resources {
        return app.resources
    }

    @Provides
    @AppScope
    fun provideApplicationContext(): Context {
        return app
    }

    @Provides
    @AppScope
    fun provideAppComponent(appComponent: AppComponent): AppComponent {
        return appComponent
    }


}
