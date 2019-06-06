package com.example.the_movie_db_app.ui.movies.mvp

import android.annotation.SuppressLint
import android.util.Log
import com.example.the_movie_db_app.R
import com.example.the_movie_db_app.app.base.models.BaseResponse
import com.example.the_movie_db_app.app.base.observer.CallbackHandlingObserver
import com.example.the_movie_db_app.ui.movies.models.MovieResult

/**
 * Created by Romel Boada on 05/06/19.
 */

class MoviesPresenter(
    private var interactor: MoviesInteractor,
    private var view: MoviesContract.View
) : MoviesContract.Presenter {


    @SuppressLint("CheckResult")
    override fun getPopularMovies(api_key: String, language: String, page: String) {
        view.showProgress(true)
        interactor.getPopularMovies(api_key, language, page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<MovieResult>>(
                this,
                "ENDPOINT_POPULAR_MOVIES"
            ) {
                override fun onSuccess(data: BaseResponse<MovieResult>) {
                    view.showProgress(false)
                    view.getPopularMoviesSuccess(data.results)
                }

            })
    }

    @SuppressLint("CheckResult")
    override fun getUpComingMovies(api_key: String, language: String, page: String) {
        view.showProgress(true)
        interactor.getUpComingMovies(api_key, language, page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<MovieResult>>(
                this,
                "ENDPOINT_POPULAR_MOVIES"
            ) {
                override fun onSuccess(data: BaseResponse<MovieResult>) {
                    view.showProgress(false)
                    view.getUpComingMoviesSuccess(data.results)
                }

            })
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(api_key: String, language: String, page: String) {
        view.showProgress(true)
        interactor.getTopRatedMovies(api_key, language, page)
            .subscribeWith(object : CallbackHandlingObserver<BaseResponse<MovieResult>>(
                this,
                "ENDPOINT_POPULAR_MOVIES"
            ) {
                override fun onSuccess(data: BaseResponse<MovieResult>) {
                    view.showProgress(false)
                    view.getTopRatedMoviesSuccess(data.results)
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