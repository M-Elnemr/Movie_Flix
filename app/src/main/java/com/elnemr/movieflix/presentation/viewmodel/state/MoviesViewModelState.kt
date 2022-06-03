package com.elnemr.movieflix.presentation.viewmodel.state

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.Results


sealed class MoviesViewModelState {
    data class OnMoviesFetched(val response: PagingData<Results>) : MoviesViewModelState()
}