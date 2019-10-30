package com.example.nasa_api.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract val TAG: String
    abstract val searchFragmentTag: String
    abstract val detailFragmentTag: String
}