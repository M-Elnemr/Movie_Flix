package com.elnemr.core.data.repository.datasource

import androidx.paging.PagingData
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Movie>>
    suspend fun fetchGenres(): GenresResponse
}