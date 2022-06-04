package com.elnemr.movieflix.domain.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable


@JsonSerializable
data class GenresResponse(
    @Json(name = "genres") val genres: List<Genre> = listOf()
)

@JsonSerializable
data class Genre (
    @Json(name = "name") val name: String? = "",
    @Json(name = "id") val id: Int? = 0
)


