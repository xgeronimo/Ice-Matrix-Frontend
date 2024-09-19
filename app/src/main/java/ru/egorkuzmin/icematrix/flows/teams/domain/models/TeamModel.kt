package ru.egorkuzmin.icematrix.flows.teams.domain.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class TeamModel(
    @Json(name = "ID")
    val id: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "City")
    val city: String,
    @Json(name = "Trainer")
    val trainer: String,
)