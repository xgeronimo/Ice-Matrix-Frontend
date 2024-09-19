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
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIState
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIState
import javax.inject.Inject


@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    private val interactor: TeamsInteractor,
) :
    BaseViewModel<TeamDetailsUIEvent, TeamDetailsUIState, TeamDetailsUIEffect>(
        TeamDetailsUIState()
    ) {

    override fun handleEvent(event: TeamDetailsUIEvent) {
        when(event){
            is TeamDetailsUIEvent.OnScreenOpened -> {
                viewModelScope.launch {
                    val result = executeRequest {
                        interactor.getTeam(event.id)
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> {
                            setState {
                                copy(
                                    team = result.value
                                )
                            }
                        }
                    }
                }

                viewModelScope.launch {
                    val result = executeRequest {
                        interactor.getTeamsPlayers(event.id)
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> {
                            setState {
                                copy(
                                    players = result.value
                                )
                            }
                        }
                    }
                }

                viewModelScope.launch {
                    val result = executeRequest {
                        interactor.getMatches(event.id)
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> {
                            setState {
                                copy(
                                    matches = result.value
                                )
                            }
                        }
                    }
                }
            }

            is TeamDetailsUIEvent.OnPlayerClicked -> {}
            is TeamDetailsUIEvent.OnAddToFavorites -> {
                viewModelScope.launch {
                    val result = executeRequest {
                        interactor.addToFavorites(event.id)
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(TeamDetailsUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> { setEffect(TeamDetailsUIEffect.ShowSnackbar("Успешно")) }
                    }
                }
            }
        }
    }
}