package com.example.the_movie_db_app.app.network.interceptor.exception

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.utils.Commons
import java.io.IOException

/**
 * Created by Romel Boada on 05/06/19.
 */

class NoConnectivityException : IOException() {

    override val message
        get() = Commons.getString(R.string.connectivity_exception)
}