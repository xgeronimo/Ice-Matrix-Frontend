package ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect

sealed class LoginUIEffect: UIEffect {
    data object OpenRegisterScreen: LoginUIEffect()
    data object OpenMainScreen: LoginUIEffect()
    data object GoBack: LoginUIEffect()

    class ShowSnackbar(val message: String): LoginUIEffect()
}