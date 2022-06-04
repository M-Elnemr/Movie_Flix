package com.elnemr.movieflix.data.repository.datasource

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Movie>>
}