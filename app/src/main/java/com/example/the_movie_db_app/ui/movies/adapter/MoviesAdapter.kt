package com.example.the_movie_db_app.ui.movies.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.the_movie_db_app.ui.movies.models.MovieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

class MoviesAdapter(
    private var result: MutableList<MovieResult>?,
    private var context: Context
) : RecyclerView.Adapter<MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder.newInstance(parent)

    override fun getItemCount() = result!!.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindViewHolder(result!![ holder.adapterPosition], holder.adapterPosition, context)
    }
}