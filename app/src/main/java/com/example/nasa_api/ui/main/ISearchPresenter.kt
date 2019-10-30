package com.example.nasa_api.ui.main

import com.example.nasa_api.ui.base.IBasePresenter

interface ISearchPresenter<T: ISearchFragment>: IBasePresenter<T> {
    fun onButtonClicked(requestString: String?)
    // onScrollRecycler
    // for update
}