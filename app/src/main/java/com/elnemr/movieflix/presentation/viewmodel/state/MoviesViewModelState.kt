package com.elnemr.movieflix.presentation.viewmodel.state

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.GenresResponse
import com.elnemr.movieflix.domain.model.Movie
import com.elnemr.movieflix.domain.result.NetworkResult


sealed class MoviesViewModelState {
    data class OnMoviesFetched(val response: PagingData<Movie>) : MoviesViewModelState()
    data class OnGenresFetched(val response: NetworkResult<GenresResponse>) : MoviesViewModelState()
}