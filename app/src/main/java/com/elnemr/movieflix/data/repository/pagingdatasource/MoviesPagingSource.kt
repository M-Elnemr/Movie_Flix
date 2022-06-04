package com.elnemr.movieflix.data.repository.pagingdatasource

import android.util.Log
import com.elnemr.movieflix.data.apiservice.ApiInterface
import com.elnemr.movieflix.data.repository.pagingdatasource.base.BasePagingSource
import com.elnemr.movieflix.data.util.Constants
import com.elnemr.movieflix.domain.model.Movie
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val apiInterface: ApiInterface) :
    BasePagingSource<Movie>() {

    lateinit var query: HashMap<String, String>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            query["page"] = page.toString()
            val response = apiInterface.fetchAllMovies(Constants.API_KEY, query)

            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.movies.size < 20) null else page + 1
            )

        } catch (e: Exception) {
            Log.d("TAG", "load: ${e.message}")

            LoadResult.Error(e)
        }
    }

}