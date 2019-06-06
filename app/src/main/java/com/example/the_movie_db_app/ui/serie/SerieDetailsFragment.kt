package com.example.the_movie_db_app.ui.serie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.ui.serie.models.SerieResult
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.fragment_serie_details.*

/**
 * Created by Romel Boada on 06/06/19.
 */

class SerieDetailsFragment : Fragment() {

    var serieResult: SerieResult? = SerieResult()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            serieResult = arguments!!.getParcelable("resultItemSerie")
            loadData(serieResult)
        }
    }

    private fun loadData(serieResult: SerieResult?) {
        Glide.with(requireContext()).
            load(Utils.getPosterUrl(serieResult!!.posterPath!!)).
            into(poster_path)

        tv_title.text = serieResult.name
        if (serieResult.originalName!!.isEmpty()) {
            original_title.visibility = View.GONE
            tv_original_title.visibility = View.GONE
        }
        tv_original_title.text = serieResult.originalName
        tv_first_air_date.text = serieResult.firstAirDate
        tv_overview.text = serieResult.overview
    }

}
