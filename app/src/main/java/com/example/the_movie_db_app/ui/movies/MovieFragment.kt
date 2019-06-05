package com.example.the_movie_db_app.ui.movies


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.App
import com.example.the_movie_db_app.app.base.fragment.BaseFragment
import com.example.the_movie_db_app.app.base.models.BaseResponse
import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.movies.di.DaggerMoviesComponent
import com.example.the_movie_db_app.ui.movies.di.MoviesModule
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import com.example.the_movie_db_app.ui.movies.mvp.MoviesContract
import com.example.the_movie_db_app.ui.movies.mvp.MoviesPresenter
import com.example.the_movie_db_app.utils.Commons
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment(), MoviesContract.View {

    @Inject
    lateinit var presenter: MoviesPresenter

    val TAG = MovieFragment::class.java.simpleName

    override fun initComponent(appComponent: AppComponent) {
       DaggerMoviesComponent.builder().
           appComponent(appComponent).
           moviesModule(MoviesModule(this)).
           build().
           inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    override fun getPopularMoviesSuccess(result: MutableList<MovieResult>?) {

        setupRecyclerView(result)

    }

    private fun setupRecyclerView(result: MutableList<MovieResult>?) {

    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

}
