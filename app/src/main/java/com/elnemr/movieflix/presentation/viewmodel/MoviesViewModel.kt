package com.elnemr.movieflix.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elnemr.movieflix.domain.model.Results
import com.elnemr.movieflix.domain.usecase.FetchAllMoviesPagingUseCase
import com.elnemr.movieflix.presentation.viewmodel.base.BaseViewModel
import com.elnemr.movieflix.presentation.viewmodel.state.MoviesViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchAllMoviesPagingUseCase: FetchAllMoviesPagingUseCase
) : BaseViewModel<MoviesViewModelState>() {

    init {
        viewModelScope.launch {
            fetchAllMoviesPagingUseCase.getStateFlow().buffer().collect { onMoviesFetched(it) }
        }
    }

    private fun onMoviesFetched(networkResult: Flow<PagingData<Results>>) {
        viewModelScope.launch {
            networkResult.cachedIn(this).collect {
                mediator.emit(MoviesViewModelState.OnMoviesFetched(it))
            }
        }
    }

    fun fetchMovies(params: HashMap<String, String>) {
        fetchAllMoviesPagingUseCase.invoke(params)
    }

}