package ru.egorkuzmin.icematrix.flows.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register.RegisterUIEffect
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register.RegisterUIEvent
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register.RegisterUIState
import ru.egorkuzmin.icematrix.core.domain.models.NetworkResult
import ru.egorkuzmin.icematrix.core.presentation.viewmodel.BaseViewModel
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) :
    BaseViewModel<RegisterUIEvent, RegisterUIState, RegisterUIEffect>(
        RegisterUIState()
    ) {

    override fun handleEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.OnEmailChanged ->
                setState { copy(email = event.email) }

            is RegisterUIEvent.OnUsernameChanged ->
                setState { copy(username = event.username) }

            is RegisterUIEvent.OnPasswordChanged ->
                setState { copy(password = event.password) }

            is RegisterUIEvent.OnPasswordConfirmationChanged ->
                setState { copy(passwordConfirmation = event.password) }

            RegisterUIEvent.OpenLoginScreen ->
                setEffect(RegisterUIEffect.OpenLoginScreen)

            RegisterUIEvent.SubmitClicked ->
                register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            val result = executeRequest {
                authInteractor.register(
                    currentState.email,
                    currentState.username,
                    currentState.password,
                )
            }
            when (result) {
                NetworkResult.NetworkError ->
                    setEffect(RegisterUIEffect.ShowSnackbar("Отсутствует подключение к интернету"))

                is NetworkResult.GenericError ->
                    setEffect(RegisterUIEffect.ShowSnackbar(result.error?.detail ?: "Ошибка"))

                is NetworkResult.Success ->
                    setEffect(RegisterUIEffect.OpenLoginScreen)
            }
        }
    }
}