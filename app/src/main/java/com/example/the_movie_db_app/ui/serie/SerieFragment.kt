package com.example.the_movie_db_app.ui.serie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.App
import com.example.the_movie_db_app.app.base.fragment.BaseFragment
import com.example.the_movie_db_app.app.di.AppComponent
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
    }

    override fun getPopularSerieSuccess(result: MutableList<SerieResult>?) {
        title.text = result!![0].name
        setupRecyclerView(result)
    }

    private fun setupRecyclerView(result: MutableList<SerieResult>?) {

    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

}
