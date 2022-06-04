package com.elnemr.movieflix.presentation.viewmodel.state

import androidx.paging.PagingData
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import com.elnemr.core.domain.result.NetworkResult


sealed class MoviesViewModelState {
    data class OnMoviesFetched(val response: PagingData<Movie>) : MoviesViewModelState()
    data class OnGenresFetched(val response: NetworkResult<GenresResponse>) : MoviesViewModelState()
}