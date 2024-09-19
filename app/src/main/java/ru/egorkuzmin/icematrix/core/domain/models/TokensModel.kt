package ru.egorkuzmin.icematrix.core.domain.models

import com.squareup.moshi.Json

data class TokensModel(
    @Json(name = "token")
    val accessToken: String,
//    @Json(name = "refresh")
//    val refreshToken: String?,
)