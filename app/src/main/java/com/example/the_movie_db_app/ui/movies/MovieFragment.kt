package com.example.the_movie_db_app.ui.movies


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController


/**
 * Created by Romel Boada on 05/06/19.
 */

class MovieFragment : BaseFragment(), MoviesContract.View, MoviesAdapter.Listener {

    @Inject
    lateinit var presenter: MoviesPresenter

    private lateinit var moviesAdapter: MoviesAdapter
    private var movieList: MutableList<MovieResult> = mutableListOf()

    val TAG = MovieFragment::class.java.simpleName

    override fun initComponent(appComponent: AppComponent) {
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(MoviesModule(this)).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent(App.get().component())
        setHasOptionsMenu(true)
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
        moviesAdapter = MoviesAdapter(movieList, requireContext(), this)
        onClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    private fun onClick() {

        val adapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.category, R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner_category.adapter = adapter

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e(TAG, TAG)
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                when (parent.selectedItemPosition) {
                    0 -> {
                        icon_movie.visibility = View.VISIBLE
                        recycler_view_movie.visibility = View.GONE
                    }
                    1 -> {
                        icon_movie.visibility = View.GONE
                        presenter.getPopularMovies(Utils.PAGE.toString())
                    }
                    2 -> {
                        icon_movie.visibility = View.GONE
                        presenter.getTopRatedMovies(Utils.PAGE.toString())
                    }
                    3 -> {
                        icon_movie.visibility = View.GONE
                        presenter.getUpComingMovies(Utils.PAGE.toString())
                    }
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun getPopularMoviesSuccess(result: MutableList<MovieResult>?) {
        recycler_view_movie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    override fun getUpComingMoviesSuccess(result: MutableList<MovieResult>?) {
        recycler_view_movie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    override fun getTopRatedMoviesSuccess(result: MutableList<MovieResult>?) {
        recycler_view_movie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    private fun setupRecyclerView(result: MutableList<MovieResult>?) {

        recycler_view_movie.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler_view_movie.setHasFixedSize(true)
        recycler_view_movie.isNestedScrollingEnabled = false

        moviesAdapter = MoviesAdapter(result, requireContext(), this)
        recycler_view_movie?.adapter = moviesAdapter
    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

    override fun onClick(resultItem: MovieResult?) {
        val bundle = Bundle()
        bundle.putParcelable("resultItem", resultItem)
        findNavController().navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.main, menu)

        val searchItem = menu!!.findItem(R.id.action_search)

        val searchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                moviesAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }

        })

    }

}
