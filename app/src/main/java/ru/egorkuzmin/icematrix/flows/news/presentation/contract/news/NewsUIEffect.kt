package ru.egorkuzmin.icematrix.flows.news.presentation.contract.news

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect

sealed class NewsUIEffect: UIEffect {
    class ShowSnackbar(val message: String): NewsUIEffect()

    class OpenNewsDetails(val id: Int): NewsUIEffect()
}