package com.example.the_movie_db_app.ui.serie.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.the_movie_db_app.ui.serie.models.SerieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieAdapter(
    private var result: MutableList<SerieResult>?,
    private var context: Context,
    private var mListener: Listener
) : RecyclerView.Adapter<SerieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SerieViewHolder.newInstance(parent)

    override fun getItemCount() = result!!.size

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        holder.bindViewHolder(result!![holder.adapterPosition], holder.adapterPosition, context, mListener)
    }

    interface Listener {
        fun onClick(resultItem: SerieResult)
    }
}