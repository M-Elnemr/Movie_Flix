package com.elnemr.movieflix.domain.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class AllMoviesResponse(
    @Json(name = "dates") val dates: Dates = Dates(),
    @Json(name = "page") val page: Long = 0,
    @Json(name = "results") val results: List<Results> = listOf(),
    @Json(name = "total_pages") val totalPages: Long = 0,
    @Json(name = "total_results") val totalResults: Long = 0,
)

@JsonSerializable
data class Dates(
    @Json(name = "maximum") val maximum: String = "",
    @Json(name = "minimum") val minimum: String = ""
)

@JsonSerializable
data class Results(
    @Json(name = "backdrop_path") val backdropPath: String = "",
    @Json(name = "genre_ids") val genreIds: List<Long> = listOf(),
    @Json(name = "id") val id: Long = 0,
    @Json(name = "original_language") val originalLanguage: String = "",
    @Json(name = "original_title") val originalTitle: String = "",
    @Json(name = "overview") val overview: String = "",
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "release_date") val releaseDate: String = "",
    @Json(name = "title") val title: String = "",
    @Json(name = "video") val video: Boolean = false,
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Long = 0,
)


