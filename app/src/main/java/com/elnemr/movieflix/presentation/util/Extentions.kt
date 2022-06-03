package com.elnemr.movieflix.presentation.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.appContext(): Context = requireActivity().applicationContext

fun View.preventDoubleClick() {
    isClickable = false
    Handler(Looper.myLooper()!!).postDelayed({ isClickable = true }, 500L)
}
