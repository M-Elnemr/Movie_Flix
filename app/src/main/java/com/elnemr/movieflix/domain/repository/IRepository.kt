package com.elnemr.movieflix.domain.repository

import androidx.paging.PagingData
import com.elnemr.movieflix.domain.model.GenresResponse
import com.elnemr.movieflix.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Movie>>
    suspend fun fetchGenres(): GenresResponse
}