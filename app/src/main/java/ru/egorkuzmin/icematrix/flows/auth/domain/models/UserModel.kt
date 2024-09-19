package ru.egorkuzmin.icematrix.flows.auth.domain.models

import com.squareup.moshi.Json

data class UserModel(
    @Json(name = "Username")
    val username: String,
    @Json(name = "Email")
    val email: String,
    @Json(name = "FavoriteTeamID")
    val favoriteTeamID: String,
)