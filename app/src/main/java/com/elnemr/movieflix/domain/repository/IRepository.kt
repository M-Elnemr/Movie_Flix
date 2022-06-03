package com.elnemr.movieflix.domain.repository

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.AllMoviesResponse
import com.elnemr.movieflix.domain.model.Results
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Results>>
}