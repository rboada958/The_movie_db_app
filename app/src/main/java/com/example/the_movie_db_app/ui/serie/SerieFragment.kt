package com.example.the_movie_db_app.ui.serie


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.App
import com.example.the_movie_db_app.app.base.fragment.BaseFragment
import com.example.the_movie_db_app.app.di.AppComponent
import com.example.the_movie_db_app.ui.serie.adapter.SerieAdapter
import com.example.the_movie_db_app.ui.serie.di.DaggerSerieComponent
import com.example.the_movie_db_app.ui.serie.di.SerieModule
import com.example.the_movie_db_app.ui.serie.models.SerieResult
import com.example.the_movie_db_app.ui.serie.mvp.SerieContract
import com.example.the_movie_db_app.ui.serie.mvp.SeriePresenter
import com.example.the_movie_db_app.utils.Commons
import com.example.the_movie_db_app.utils.Utils
import kotlinx.android.synthetic.main.fragment_serie.*
import javax.inject.Inject

/**
 * Created by Romel Boada on 05/06/19.
 */

class SerieFragment : BaseFragment(), SerieContract.View {

    @Inject
    lateinit var presenter: SeriePresenter

    val TAG = SerieFragment::class.java.simpleName

    override fun initComponent(appComponent: AppComponent) {
        DaggerSerieComponent.builder().
            appComponent(appComponent).
            serieModule(SerieModule(this)).
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
        return inflater.inflate(R.layout.fragment_serie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getPopularSerie(Utils.API_KEY, Utils.LANGUAGE, Utils.PAGE.toString())

        onClick()
    }

    private fun onClick() {

        val adapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.category_serie, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_category.adapter = adapter

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)

                val item = parent.selectedItemPosition
                Log.e(TAG, "$item")
                if (item > 0) {
                    when (item) {
                        1 -> {
                            presenter.getPopularSerie(Utils.API_KEY, Utils.LANGUAGE, Utils.PAGE.toString())
                        }
                        2 -> {
                            presenter.getTopRatedSerie(Utils.API_KEY, Utils.LANGUAGE, Utils.PAGE.toString())
                        }
                        3 -> {
                            presenter.getOnTheAirSerie(Utils.API_KEY, Utils.LANGUAGE, Utils.PAGE.toString())
                        }
                    }
                }
            }
        }

    }

    override fun getPopularSerieSuccess(result: MutableList<SerieResult>?) {
        recycler_view_serie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    override fun getTopRatedSerieSuccess(result: MutableList<SerieResult>?) {
        recycler_view_serie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    override fun getOnTheAirSerieSuccess(result: MutableList<SerieResult>?) {
        recycler_view_serie.visibility = View.VISIBLE
        setupRecyclerView(result)
    }

    private fun setupRecyclerView(result: MutableList<SerieResult>?) {
        recycler_view_serie.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler_view_serie.setHasFixedSize(true)
        recycler_view_serie.isNestedScrollingEnabled = false

        recycler_view_serie?.adapter = SerieAdapter(result, requireContext())
    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

}
