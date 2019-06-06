package com.example.the_movie_db_app.ui.serie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.ui.serie.models.SerieResult
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.item_list_movie.view.*

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    companion object {
        fun newInstance(parent: ViewGroup) = SerieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_movie,
                parent,
                false
            )
        )
    }

    fun bindViewHolder(
        serieResult: SerieResult,
        adapterPosition: Int,
        context: Context,
        mListener: SerieAdapter.Listener
    ) {

        itemView.tv_title.text = serieResult.name
        itemView.tv_rated.text = serieResult.voteAverage.toString()

        Glide.with(context)
            .load(Utils.getBackDropUrl(serieResult.backdropPath!!))
            .into(itemView.iv_path)

        itemView.setOnClickListener {
            mListener.onClick(serieResult)
        }
    }
}