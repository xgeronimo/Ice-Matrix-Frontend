package ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect

sealed class RegisterUIEffect: UIEffect {
    data object OpenLoginScreen: RegisterUIEffect()
    data object GoBack: RegisterUIEffect()

    class ShowSnackbar(val message: String): RegisterUIEffect()
}