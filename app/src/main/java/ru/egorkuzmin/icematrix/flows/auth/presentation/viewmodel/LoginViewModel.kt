package ru.egorkuzmin.icematrix.flows.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egorkuzmin.icematrix.core.domain.models.NetworkResult
import ru.egorkuzmin.icematrix.core.presentation.viewmodel.BaseViewModel
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login.LoginUIEffect
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login.LoginUIEvent
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login.LoginUIState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) :
    BaseViewModel<LoginUIEvent, LoginUIState, LoginUIEffect>(
        LoginUIState()
    ) {

    override fun handleEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnEmailOrUsernameChanged -> {
                setState { copy(emailOrUsername = event.value) }
            }

            is LoginUIEvent.OnPasswordChanged ->
                setState { copy(password = event.password) }

            LoginUIEvent.OpenRegisterScreen ->
                setEffect(LoginUIEffect.OpenRegisterScreen)

            LoginUIEvent.SubmitClicked ->
                login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            val result = executeRequest {
                authInteractor.login(
                    currentState.emailOrUsername,
                    currentState.password
                )
            }
            when (result) {
                NetworkResult.NetworkError ->
                    setEffect(LoginUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                is NetworkResult.GenericError ->
                    setEffect(LoginUIEffect.ShowSnackbar(result.error?.detail ?: "Ошибка"))

                is NetworkResult.Success ->
                    setEffect(LoginUIEffect.OpenMainScreen)
            }
        }
    }
}