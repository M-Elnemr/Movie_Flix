package com.elnemr.core.data.apiservice

import com.elnemr.core.domain.model.AllMoviesResponse
import com.elnemr.core.domain.model.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiInterface {
    // api_key provided from the network module "KeyInterceptor" as we need it at every call

    @GET("discover/movie")
    suspend fun fetchAllMovies(
        @QueryMap params: Map<String, String>,
//        @Query("with_genres") genre_id: String?,
//        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean = false
    ): AllMoviesResponse

    @GET("genre/movie/list")
    suspend fun fetchAllGenre(): GenresResponse
}