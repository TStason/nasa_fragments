package com.example.nasa_api.ui.base

interface IBasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
}