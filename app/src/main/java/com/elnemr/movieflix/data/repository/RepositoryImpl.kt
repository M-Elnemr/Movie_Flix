package com.elnemr.movieflix.data.repository

import androidx.paging.PagingData
import com.elnemr.movieflix.data.repository.datasource.RemoteDataSource
import com.elnemr.movieflix.domain.model.Results
import com.elnemr.movieflix.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IRepository {
    override suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Results>> =
        remoteDataSource.fetchAllMovies(params)
}