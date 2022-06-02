package com.elnemr.movieflix.data.apiservice

import com.elnemr.movieflix.domain.model.AllMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface ApiInterface {
    @GET("discover/movie")
    fun fetchAllMovies(
        @Query("api_key") apiKey: String?,
        @QueryMap params: Map<String, String>,
//        @Query("with_genres") genre_id: String?,
//        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean = false
    ): AllMoviesResponse
}