package ru.egorkuzmin.icematrix.flows.news.domain.interactor

import ru.egorkuzmin.icematrix.flows.news.data.repository.NewsRepository
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend fun getNews(): List<NewsModel> =
        repository.getNews()

    suspend fun getNewsDetail(id: Int): NewsModel =
        repository.getNews(id)
}