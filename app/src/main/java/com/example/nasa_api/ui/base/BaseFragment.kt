package com.example.nasa_api.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract val TAG: String

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated -> $view")
    }
    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }
    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }
    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }
    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }
}