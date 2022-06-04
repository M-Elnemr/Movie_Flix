package com.elnemr.core.data.repository

import androidx.paging.PagingData
import com.elnemr.core.data.repository.datasource.RemoteDataSource
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import com.elnemr.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IRepository {
    override suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Movie>> =
        remoteDataSource.fetchAllMovies(params)

    override suspend fun fetchGenres(): GenresResponse = remoteDataSource.fetchGenres()
}