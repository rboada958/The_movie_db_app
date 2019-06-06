package com.example.the_movie_db_app.ui.serie.mvp

import android.annotation.SuppressLint
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.base.models.BaseResponse
import com.example.the_movie_db_app.app.base.observer.CallbackHandlingObserver
import com.example.the_movie_db_app.ui.serie.models.SerieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

class SeriePresenter(
    private var interactor: SerieInteractor,
    private var view: SerieContract.View
) : SerieContract.Presenter {

    @SuppressLint("CheckResult")
    override fun getPopularSerie(page: String) {
        view.showProgress(true)
        interactor.getPopularSerie(page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<SerieResult>>(
                this,
                "ENDPOINT_POPULAR_SERIES"
            ) {
                override fun onSuccess(data: BaseResponse<SerieResult>) {
                    view.showProgress(false)
                    view.getPopularSerieSuccess(data.results)
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedSerie(page: String) {
        view.showProgress(true)
        interactor.getTopRatedSerie(page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<SerieResult>>(
                this,
                "ENDPOINT_POPULAR_SERIES"
            ) {
                override fun onSuccess(data: BaseResponse<SerieResult>) {
                    view.showProgress(false)
                    view.getTopRatedSerieSuccess(data.results)
                }
            })
    }

    @SuppressLint("CheckResult")
    override fun getOnTheAirSerie(page: String) {
        view.showProgress(true)
        interactor.getOnTheAirSerie(page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<SerieResult>>(
                this,
                "ENDPOINT_POPULAR_SERIES"
            ) {
                override fun onSuccess(data: BaseResponse<SerieResult>) {
                    view.showProgress(false)
                    view.getOnTheAirSerieSuccess(data.results)
                }
            })
    }

    override fun destroyView() {
    }

    override fun onUnknownError(error: String, caller: String) {
        view.showProgress(false)
        genericError(R.string.error_unknown)
    }

    override fun onTimeoutError(caller: String) {
        view.showProgress(false)
        genericError(R.string.error_timeout)
    }

    override fun onNetworkError(caller: String) {
        view.showProgress(false)
        genericError(R.string.error_network)
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        view.showProgress(false)
        genericError(R.string.error_badrequest)
    }

    override fun onServerError(caller: String) {
        view.showProgress(false)
        genericError(R.string.error_server)
    }

    override fun infoError(cause: Throwable?, msg: String?) {
        view.showProgress(false)
    }

    private fun genericError(msg: Int) {
        view.showProgress(false)
        view.makeToast(msg)
    }
}