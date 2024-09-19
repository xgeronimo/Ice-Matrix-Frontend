package ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent


sealed class LoginUIEvent : UIEvent {
    class OnEmailOrUsernameChanged(val value: String): LoginUIEvent()
    class OnPasswordChanged(val password: String): LoginUIEvent()


    data object OpenRegisterScreen: LoginUIEvent()
    data object SubmitClicked: LoginUIEvent()
}