package ru.egorkuzmin.icematrix.flows.teams.domain.models

import com.squareup.moshi.Json

data class FavoriteTeamModel(
    @Json(name = "FavoriteTeamID")
    val favoriteTeamID: String
)