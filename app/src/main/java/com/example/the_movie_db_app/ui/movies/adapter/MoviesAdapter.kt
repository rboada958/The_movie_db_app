package com.example.the_movie_db_app.ui.movies.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.the_movie_db_app.ui.movies.models.MovieResult
import java.util.ArrayList

/**
 * Created by Romel Boada on 05/06/19.
 */

class MoviesAdapter(
    private var result: MutableList<MovieResult>?,
    private var context: Context,
    private var mListener: Listener
) : RecyclerView.Adapter<MoviesViewHolder>(), Filterable {

    private var resultFull: MutableList<MovieResult> = result!!

    override fun getFilter(): Filter {
        return listFilter
    }

    private val listFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList : MutableList<MovieResult> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(resultFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in resultFull) {
                    if (item.title!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredList

            return results

        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            result!!.clear()
            result!!.addAll(results.values as Collection<MovieResult>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder.newInstance(parent)

    override fun getItemCount() = result!!.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindViewHolder(result!![ holder.adapterPosition], holder.adapterPosition, context, mListener)
    }

    interface Listener {
        fun onClick(resultItem: MovieResult?)
    }
}