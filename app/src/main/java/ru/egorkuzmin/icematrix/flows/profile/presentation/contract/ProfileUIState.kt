package ru.egorkuzmin.icematrix.flows.profile.presentation.contract

import ru.egorkuzmin.icematrix.core.presentation.contract.UIState


data class ProfileUIState(
    val username: String? = "",
    val email: String? = "",
    val favoriteTeamId: String? = "",
): UIState