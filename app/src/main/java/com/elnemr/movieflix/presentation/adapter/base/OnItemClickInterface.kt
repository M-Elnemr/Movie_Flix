package com.elnemr.movieflix.presentation.adapter.base

import android.view.View

interface OnItemClickInterface {
    fun onDetailsClicked(data: Any, view: View? = null)
}