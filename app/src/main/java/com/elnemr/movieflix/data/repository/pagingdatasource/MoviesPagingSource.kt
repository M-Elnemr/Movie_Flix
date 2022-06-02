package com.elnemr.movieflix.data.repository.pagingdatasource

import com.elnemr.movieflix.data.apiservice.ApiInterface
import com.elnemr.movieflix.data.repository.pagingdatasource.base.BasePagingSource
import com.elnemr.movieflix.data.util.Constants
import com.elnemr.movieflix.domain.model.Results
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val apiInterface: ApiInterface) :
    BasePagingSource<Results>() {

    lateinit var query: HashMap<String, String>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val page = params.key ?: 1

        return try {

            query["page"] = page.toString()
            val response = apiInterface.fetchAllMovies(Constants.API_KEY, query)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.size < 10) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}