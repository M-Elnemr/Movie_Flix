package com.elnemr.core.data.repository.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elnemr.core.data.apiservice.ApiInterface
import com.elnemr.core.data.repository.pagingdatasource.MoviesPagingSource
import com.elnemr.core.domain.model.GenresResponse
import com.elnemr.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    RemoteDataSource {

    override suspend fun fetchAllMovies(params: HashMap<String, String>): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviesPagingSource(apiInterface).apply {
                    this.query = params
                }
            }
        ).flow

    override suspend fun fetchGenres(): GenresResponse = apiInterface.fetchAllGenre()


}