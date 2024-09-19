package ru.egorkuzmin.icematrix.flows.profile.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEffect
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEvent
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIState
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.core.domain.models.NetworkResult
import ru.egorkuzmin.icematrix.core.presentation.viewmodel.BaseViewModel
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEffect
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val tokensInteractor: TokensInteractor,
) : BaseViewModel<ProfileUIEvent, ProfileUIState, ProfileUIEffect>(
    ProfileUIState()
) {

    init {
        setState {
            copy(
                username = tokensInteractor.username,
                email = tokensInteractor.email
            )
        }
    }

    override fun handleEvent(event: ProfileUIEvent) {
        when (event) {
            ProfileUIEvent.OnScreenOpened -> {
                setState {
                    copy(
                        username = tokensInteractor.username,
                        email = tokensInteractor.email
                    )
                }

                viewModelScope.launch {
                    val result = executeRequest {
                        authInteractor.getUser()
                    }
                    when (result) {
                        is NetworkResult.GenericError ->
                            setEffect(ProfileUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                        NetworkResult.NetworkError ->
                            setEffect(ProfileUIEffect.ShowSnackbar("Ошибка на стороне сервера"))

                        is NetworkResult.Success -> {
                            setState {
                                copy(
                                    username = result.value.username,
                                    email = result.value.email,
                                    favoriteTeamId = result.value.favoriteTeamID,
                                )
                            }
                        }
                    }
                }
            }

            ProfileUIEvent.OnLogoutClicked -> {
                authInteractor.logout()
                setEffect(ProfileUIEffect.Logout)
            }
        }
    }
}