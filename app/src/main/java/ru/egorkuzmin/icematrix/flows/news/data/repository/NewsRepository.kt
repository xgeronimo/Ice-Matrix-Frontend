package ru.egorkuzmin.icematrix.flows.news.data.repository

import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.flows.auth.data.api.AuthApi
import ru.egorkuzmin.icematrix.flows.auth.domain.models.LoginRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.RegisterRequest
import ru.egorkuzmin.icematrix.flows.news.data.api.NewsApi
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel

class NewsRepository(
    private val api: NewsApi,
) {

    suspend fun getNews(): List<NewsModel> = api.news()

    suspend fun getNews(id: Int): NewsModel = api.newsDetail(id)
}