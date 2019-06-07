package com.example.the_movie_db_app.app.network.di

import android.content.Context
import android.os.Build
import com.example.the_movie_db_app.BuildConfig
import com.example.the_movie_db_app.app.App
import com.example.the_movie_db_app.app.di.AppModule
import com.example.the_movie_db_app.app.di.AppScope
import com.example.the_movie_db_app.app.network.interceptor.ConnectivityInterceptor
import com.example.the_movie_db_app.utils.Utils.hasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    @AppScope

    fun provideInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @AppScope
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10MB Cahe
    }

    @Provides
    @AppScope
    fun provideCacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .addInterceptor { chain ->

                var request = chain.request()
                request = if (hasNetwork(App.get())!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }
}