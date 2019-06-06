package com.example.the_movie_db_app.ui.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.item_list_movie.view.*

/**
 * Created by Romel Boada on 05/06/19.
 */

class MoviesViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    companion object {
        fun newInstance(parent: ViewGroup) = MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_movie,
                parent,
                false
            )
        )
    }

    fun bindViewHolder(
        movieResult: MovieResult,
        adapterPosition: Int,
        context: Context,
        mListener: MoviesAdapter.Listener
    ) {

        itemView.tv_title.text = movieResult.title
        itemView.tv_rated.text = movieResult.voteAverage.toString()

        Glide.with(context)
            .load(Utils.getBackDropUrl(movieResult.backdropPath!!))
            .into(itemView.iv_path)

        itemView.setOnClickListener {
            mListener.onClick(movieResult)
        }
    }
}