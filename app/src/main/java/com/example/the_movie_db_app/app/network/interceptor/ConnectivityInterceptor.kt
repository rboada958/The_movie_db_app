package com.example.the_movie_db_app.app.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.the_movie_db_app.app.network.interceptor.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Romel Boada on 05/06/19.
 */

class ConnectivityInterceptor(var context: Context) : Interceptor {

    companion object {
        @JvmStatic
        fun isOnline(c: Context): Boolean {
            val connectivityManager: ConnectivityManager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.activeNetworkInfo == null)
                return false
            val netInfo: NetworkInfo = connectivityManager.activeNetworkInfo;
            return (netInfo.isConnected)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline(context)) {
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}