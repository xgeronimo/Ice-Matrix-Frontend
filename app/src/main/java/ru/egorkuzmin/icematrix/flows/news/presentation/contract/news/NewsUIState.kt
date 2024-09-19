package ru.egorkuzmin.icematrix.flows.news.presentation.contract.news

import ru.egorkuzmin.icematrix.core.presentation.contract.UIState
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel


data class NewsUIState(
    val news: List<NewsModel> = emptyList()
) : UIState