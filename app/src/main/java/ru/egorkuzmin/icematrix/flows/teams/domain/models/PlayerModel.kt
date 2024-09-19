package ru.egorkuzmin.icematrix.flows.teams.domain.models

import com.squareup.moshi.Json

data class PlayerModel(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Position")
    val position: String,
    @Json(name = "DateOfBirth")
    val dateOfBirth: String,
    @Json(name = "Weight")
    val weight: Int,
    @Json(name = "Height")
    val height: Int,
    @Json(name = "Number")
    val number: Int,
)
