package ru.egorkuzmin.icematrix.core.domain.models

import com.squareup.moshi.Json

data class AccessTokenModel(

    @Json(name = "token")
    val token: String
)