package com.example.the_movie_db_app.ui.serie


import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
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

class SerieFragment : BaseFragment(), SerieContract.View, SerieAdapter.Listener {

    @Inject
    lateinit var presenter: SeriePresenter

    private var serieList: MutableList<SerieResult> = mutableListOf()
    private lateinit var serieAdapter: SerieAdapter

    val TAG = SerieFragment::class.java.simpleName

    override fun initComponent(appComponent: AppComponent) {
        DaggerSerieComponent.builder().appComponent(appComponent).serieModule(SerieModule(this)).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent(App.get().component())
        setHasOptionsMenu(true)
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
        serieAdapter = SerieAdapter(serieList, requireContext(), this)
        onClick()
    }

    private fun onClick() {

        val adapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.category_serie, R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner_category.adapter = adapter

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {


                when (parent.selectedItemPosition) {
                    0 -> {
                        icon_serie.visibility = View.VISIBLE
                        recycler_view_serie.visibility = View.GONE
                    }
                    1 -> {
                        icon_serie.visibility = View.GONE
                        presenter.getPopularSerie(Utils.PAGE.toString())
                    }
                    2 -> {
                        icon_serie.visibility = View.GONE
                        presenter.getTopRatedSerie(Utils.PAGE.toString())
                    }
                    3 -> {
                        icon_serie.visibility = View.GONE
                        presenter.getOnTheAirSerie(Utils.PAGE.toString())
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
        serieList = result!!
        serieAdapter = SerieAdapter(result, requireContext(), this)

        recycler_view_serie?.adapter = serieAdapter
    }

    override fun showProgress(isShow: Boolean) {
        progressBar?.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun makeToast(msg: Int) {
        Commons.makeToast(getString(msg), requireContext())
    }

    override fun onClick(resultItem: SerieResult) {
        val bundle = Bundle()
        bundle.putParcelable("resultItemSerie", resultItem)
        findNavController().navigate(R.id.action_serieFragment_to_serieDetailsFragment, bundle)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.main, menu)

        val searchItem = menu!!.findItem(R.id.action_search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.imeOptions = EditorInfo.IME_ACTION_DONE

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                serieAdapter.filter.filter(newText)
                return false
            }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    // task HERE
                    return true
                }

            })
        }
    }

}
