package ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent


sealed class NewsDetailUIEvent : UIEvent {
    class OnScreenOpened(val id: Int): NewsDetailUIEvent()
}