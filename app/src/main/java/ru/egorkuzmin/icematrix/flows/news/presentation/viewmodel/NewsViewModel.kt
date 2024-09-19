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
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor,
) :
    BaseViewModel<NewsUIEvent, NewsUIState, NewsUIEffect>(
        NewsUIState()
    ) {

    init {
        viewModelScope.launch {
            val result = executeRequest {
                newsInteractor.getNews()
            }
            when (result) {
                is NetworkResult.GenericError ->
                    setEffect(NewsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                NetworkResult.NetworkError ->
                    setEffect(NewsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

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

    override fun handleEvent(event: NewsUIEvent) {
        when(event){
            is NewsUIEvent.OnCardClicked -> {
                setEffect(NewsUIEffect.OpenNewsDetails(event.id))
            }
        }
    }
}