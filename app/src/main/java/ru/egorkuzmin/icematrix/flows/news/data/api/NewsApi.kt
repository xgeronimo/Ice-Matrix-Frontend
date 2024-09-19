package ru.egorkuzmin.icematrix.flows.news.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.egorkuzmin.icematrix.core.domain.models.TokensModel
import ru.egorkuzmin.icematrix.flows.auth.domain.models.LoginRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.RegisterRequest
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel

interface NewsApi {

    @GET("news/")
    suspend fun news(): List<NewsModel>

    @GET("news/{id}")
    suspend fun newsDetail(@Path("id") id: Int): NewsModel
}