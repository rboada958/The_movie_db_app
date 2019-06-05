package com.example.the_movie_db_app.app.network.di

import android.content.res.Resources
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.di.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Romel Boada on 05/06/19.
 */

@Module(includes = [NetworkModule::class])
class RetrofitModule {

    @Provides
    @AppScope
    fun provideBaseUrl(resources: Resources): String {
        return resources.getString(R.string.api_url)
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gson: Gson,
                        url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(url)
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.readTimeout(50, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(50, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(50, TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }
}