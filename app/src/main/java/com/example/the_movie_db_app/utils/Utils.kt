package com.example.the_movie_db_app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Romel Boada on 05/06/19.
 */

object Utils {

    private const val imageUrlMedium = "https://image.tmdb.org/t/p/w300"
    private const val imageUrlLarge = "https://image.tmdb.org/t/p/w500"

    var API_KEY = "e4ef5a469e652d9c0a466101877b46a6"
    var LANGUAGE = "en"
    var PAGE = 1

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    fun getPosterUrl(path: String) = imageUrlMedium + path

    fun getBackDropUrl(path: String) = imageUrlLarge + path

}