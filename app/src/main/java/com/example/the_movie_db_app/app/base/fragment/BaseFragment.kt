package com.example.the_movie_db_app.app.base.fragment

import androidx.fragment.app.Fragment
import com.example.the_movie_db_app.app.di.AppComponent

/**
 * Created by Romel Boada on 05/06/19.
 */

abstract class BaseFragment : Fragment() {

    protected abstract fun initComponent(appComponent: AppComponent)
}