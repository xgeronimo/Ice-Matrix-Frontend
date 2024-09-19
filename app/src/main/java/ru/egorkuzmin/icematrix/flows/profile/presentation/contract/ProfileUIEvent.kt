package ru.egorkuzmin.icematrix.flows.profile.presentation.contract

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent


sealed class ProfileUIEvent: UIEvent {
    data object OnScreenOpened : ProfileUIEvent()
    data object OnLogoutClicked : ProfileUIEvent()
}