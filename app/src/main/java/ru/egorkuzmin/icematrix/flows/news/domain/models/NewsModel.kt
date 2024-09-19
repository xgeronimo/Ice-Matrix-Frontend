package ru.egorkuzmin.icematrix.flows.news.domain.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class NewsModel(
    @Json(name = "ID")
    val id: Int,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Content")
    val content: String,
)