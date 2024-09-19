package ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent


sealed class RegisterUIEvent : UIEvent {
    class OnEmailChanged(val email: String): RegisterUIEvent()
    class OnUsernameChanged(val username: String): RegisterUIEvent()
    class OnPasswordChanged(val password: String): RegisterUIEvent()
    class OnPasswordConfirmationChanged(val password: String): RegisterUIEvent()

    data object OpenLoginScreen: RegisterUIEvent()
    data object SubmitClicked: RegisterUIEvent()
}