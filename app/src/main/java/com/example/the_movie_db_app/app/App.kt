package com.example.the_movie_db_app.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.app.di.AppModule
import com.example.the_movie_db_app.app.di.DaggerAppComponent
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider

/**
 * Created by Romel Boada on 05/06/19.
 */

class App : Application() {

    lateinit var component: AppComponent

    companion object {

        @JvmStatic
        lateinit var INSTANCE: App

        @JvmStatic
        fun get() : App  {
            return INSTANCE
        }

        @JvmStatic
        fun getAppContext() : Context {
            return INSTANCE.baseContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        initComponent()

        EmojiManager.install(GoogleEmojiProvider())

    }

    fun initComponent() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(INSTANCE)).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun component(): AppComponent {
        return component
    }
}