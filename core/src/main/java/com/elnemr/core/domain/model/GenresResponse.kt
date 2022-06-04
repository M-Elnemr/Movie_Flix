package com.elnemr.core.domain.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class GenresResponse(
    @Json(name = "genres") val genres: List<Genre> = listOf()
)

@JsonSerializable
data class Genre (
    @Json(name = "name") val name: String? = "",
    @Json(name = "id") val id: Int? = 0
)


