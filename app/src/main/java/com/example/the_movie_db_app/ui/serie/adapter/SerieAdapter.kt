package com.example.the_movie_db_app.ui.serie.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.the_movie_db_app.ui.serie.models.SerieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieAdapter(
    private var result: MutableList<SerieResult>?,
    private var context: Context,
    private var mListener: Listener
) : RecyclerView.Adapter<SerieViewHolder>(), Filterable {

    private var resultFull: MutableList<SerieResult> = result!!

    override fun getFilter(): Filter {
        return listFilter
    }

    private val listFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList : MutableList<SerieResult> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(resultFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in resultFull) {
                    if (item.name!!.toLowerCase().contains(filterPattern)) {
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
            result!!.addAll(results.values as Collection<SerieResult>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SerieViewHolder.newInstance(parent)

    override fun getItemCount() = result!!.size

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        holder.bindViewHolder(result!![holder.adapterPosition], holder.adapterPosition, context, mListener)
    }

    interface Listener {
        fun onClick(resultItem: SerieResult)
    }
}