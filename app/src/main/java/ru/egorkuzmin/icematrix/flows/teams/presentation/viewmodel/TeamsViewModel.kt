package ru.egorkuzmin.icematrix.flows.teams.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIState
import ru.egorkuzmin.icematrix.core.domain.models.NetworkResult
import ru.egorkuzmin.icematrix.core.presentation.viewmodel.BaseViewModel
import ru.egorkuzmin.icematrix.flows.news.domain.interactor.NewsInteractor
import ru.egorkuzmin.icematrix.flows.teams.domain.interactor.TeamsInteractor
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIState
import javax.inject.Inject


@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val interactor: TeamsInteractor,
) :
    BaseViewModel<TeamsUIEvent, TeamsUIState, TeamsUIEffect>(
        TeamsUIState()
    ) {

    init {
        viewModelScope.launch {
            val result = executeRequest {
                interactor.getTeams()
            }
            when (result) {
                is NetworkResult.GenericError ->
                    setEffect(TeamsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                NetworkResult.NetworkError ->
                    setEffect(TeamsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                is NetworkResult.Success -> {
                    setState {
                        copy(
                            teams = result.value
                        )
                    }
                }
            }
        }
    }

    override fun handleEvent(event: TeamsUIEvent) {
        when(event){
            is TeamsUIEvent.OnTeamClicked ->
                setEffect(TeamsUIEffect.OpenTeamDetails(event.team))
        }
    }
}