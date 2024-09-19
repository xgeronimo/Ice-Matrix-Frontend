package ru.egorkuzmin.icematrix.flows.news.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIState
import ru.egorkuzmin.icematrix.core.domain.models.NetworkResult
import ru.egorkuzmin.icematrix.core.presentation.viewmodel.BaseViewModel
import ru.egorkuzmin.icematrix.flows.news.domain.interactor.NewsInteractor
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIState
import javax.inject.Inject


@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor,
) : BaseViewModel<NewsDetailUIEvent, NewsDetailUIState, NewsDetailUIEffect>(
        NewsDetailUIState()
    ) {

    override fun handleEvent(event: NewsDetailUIEvent) {
        when(event){
            is NewsDetailUIEvent.OnScreenOpened ->{
                viewModelScope.launch {
                    val result = executeRequest {
                        newsInteractor.getNewsDetail(event.id)
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(NewsDetailUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(NewsDetailUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> {
                            setState {
                                copy(
                                    news = result.value
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}