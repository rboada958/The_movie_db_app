package com.example.the_movie_db_app.app.base.mvp


/**
 * Created by Romel Boada on 05/06/19.
 */

interface BaseContract {

    interface View {
        fun showProgress(isShow: Boolean)
        fun makeToast(msg:Int)
    }

    interface Presenter{

        fun destroyView()

    }

    interface ServicePresenter: Presenter {
        fun onUnknownError(error: String, caller: String)

        fun onTimeoutError(caller: String)

        fun onNetworkError(caller: String)

        fun onBadRequestError(caller: String, codeError: Int)

        fun onServerError(caller: String)

        fun infoError(cause: Throwable?, msg: String?)

    }
}