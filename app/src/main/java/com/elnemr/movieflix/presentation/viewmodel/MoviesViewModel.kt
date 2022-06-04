package com.elnemr.movieflix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import com.elnemr.core.domain.result.NetworkResult
import com.elnemr.core.domain.usecase.FetchAllMoviesPagingUseCase
import com.elnemr.core.domain.usecase.FetchGenresUseCase
import com.elnemr.movieflix.presentation.viewmodel.base.BaseViewModel
import com.elnemr.movieflix.presentation.viewmodel.state.MoviesViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchAllMoviesPagingUseCase: FetchAllMoviesPagingUseCase,
    private val fetchGenresUseCase: FetchGenresUseCase
) : BaseViewModel<MoviesViewModelState>() {

    init {
        viewModelScope.launch {
            // every action into launch block to work concurrency
            launch {
                fetchAllMoviesPagingUseCase.getStateFlow().buffer().collect { onMoviesFetched(it) }
            }
            launch {
                fetchGenresUseCase.getStateFlow().buffer().collect { onGenresFetched(it) }
            }
        }
    }

    private fun onGenresFetched(networkResult: NetworkResult<GenresResponse>) {
        viewModelScope.launch {
            mediator.emit(MoviesViewModelState.OnGenresFetched(networkResult))
        }
    }

    private fun onMoviesFetched(networkResult: Flow<PagingData<Movie>>) {
        viewModelScope.launch {
            networkResult.cachedIn(this).collect {
                mediator.emit(MoviesViewModelState.OnMoviesFetched(it))
            }
        }
    }

    fun fetchMovies(params: HashMap<String, String>) =
        fetchAllMoviesPagingUseCase.invoke(params)

    fun fetchGenres() =
        fetchGenresUseCase.invoke()
}