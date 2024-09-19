package ru.egorkuzmin.icematrix.flows.profile.presentation.contract

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect

sealed class ProfileUIEffect: UIEffect {

    class ShowSnackbar(val message: String): ProfileUIEffect()
    data object Logout: ProfileUIEffect()
}