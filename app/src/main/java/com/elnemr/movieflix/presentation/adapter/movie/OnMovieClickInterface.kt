package com.elnemr.movieflix.presentation.adapter.movie

import android.view.View
import com.elnemr.core.domain.model.Movie

interface OnMovieClickInterface {
    fun onDetailsClicked(data: Movie, view: View)
}