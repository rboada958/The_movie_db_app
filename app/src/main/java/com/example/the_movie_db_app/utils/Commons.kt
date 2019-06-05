package com.example.the_movie_db_app.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import com.example.the_movie_db_app.app.App

/**
 * Created by Romel Boada on 05/06/19.
 */

object Commons {

    fun makeToast(msg: String, context: Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun getString(string: Int): String {
        val result: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            result = App.getAppContext().getString(string)
        else
            result = App.getAppContext().getString(string)
        return result
    }
}