package com.example.the_movie_db_app.ui.movies


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.App
import com.example.the_movie_db_app.app.base.fragment.BaseFragment
import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.movies.adapter.MoviesAdapter
import com.example.the_movie_db_app.ui.movies.di.DaggerMoviesComponent
import com.example.the_movie_db_app.ui.movies.di.MoviesModule
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import com.example.the_movie_db_app.ui.movies.mvp.MoviesContract
import com.example.the_movie_db_app.ui.movies.mvp.MoviesPresenter
import com.example.the_movie_db_app.utils.Commons
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

/**
 * Created by Romel Boada on 05/06/19.
 */

class MovieFragment : BaseFragment(), MoviesContract.View {

    @Inject
    lateinit var presenter: MoviesPresenter

    val TAG = MovieFragment::class.java.simpleName

    override fun initComponent(appComponent: AppComponent) {
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(MoviesModule(this)).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent(App.get().component())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getPopularMovies(Utils.API_KEY, Utils.LANGUAGE, Utils.PAGE.toString())
    }

    @SuppressLint("CheckResult")
    override fun getPopularMoviesSuccess(result: MutableList<MovieResult>?) {
        recycler_view_movie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    private fun setupRecyclerView(result: MutableList<MovieResult>?) {

        recycler_view_movie.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler_view_movie.setHasFixedSize(true)
        recycler_view_movie.isNestedScrollingEnabled = false

        recycler_view_movie?.adapter = MoviesAdapter(result, requireContext())
    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

}
