package com.example.nasa_api.ui.base

open class BasePresenter<T>: IBasePresenter<T> {

    private var view: T? = null


    fun isViewAttached() = view != null
    fun getView() = view


    override fun attachView(view: T) {
        this.view = view
    }
    override fun detachView() {
        view = null
    }
}