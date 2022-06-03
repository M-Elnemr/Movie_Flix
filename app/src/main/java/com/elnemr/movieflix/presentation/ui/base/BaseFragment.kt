package com.elnemr.movieflix.presentation.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(layoutResourceID: Int) : Fragment(layoutResourceID) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModelStateObservers()
    }

    abstract fun setUpViewModelStateObservers()

}