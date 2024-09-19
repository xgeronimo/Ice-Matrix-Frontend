package ru.egorkuzmin.icematrix.flows.news.presentation.contract.news

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent


sealed class NewsUIEvent : UIEvent {
    class OnCardClicked(val id: Int): NewsUIEvent()
}