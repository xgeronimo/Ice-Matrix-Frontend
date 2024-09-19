package ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail

import ru.egorkuzmin.icematrix.core.presentation.contract.UIState
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel


data class NewsDetailUIState(
    val news: NewsModel? = null
) : UIState