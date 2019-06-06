package com.example.the_movie_db_app.ui.movies


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

    val TAG = MovieDetailsFragment::class.java.simpleName
    var movieResult: MovieResult? = MovieResult()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()

        if (arguments != null) {
            arguments!!.getParcelable<MovieResult>("resultItem")
            movieResult =  arguments!!.getParcelable("resultItem")
            loadData(movieResult)
        }
    }

    private fun loadData(movieResult: MovieResult?) {
        Glide.with(requireContext()).
            load(Utils.getPosterUrl(movieResult!!.posterPath!!)).
            into(poster_path)

        tv_title.text = movieResult.title
        if (movieResult.originalTitle!!.isEmpty()) {
            original_title.visibility = View.GONE
            tv_original_title.visibility = View.GONE
        }
        tv_original_title.text = movieResult.originalTitle
        tv_release_date.text = movieResult.releaseDate
        tv_overview.text = movieResult.overview
    }

    private fun onClick() {
    }

}
