package ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect

sealed class NewsDetailUIEffect: UIEffect {
    class ShowSnackbar(val message: String): NewsDetailUIEffect()
}