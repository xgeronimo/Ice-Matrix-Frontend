package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams

import ru.egorkuzmin.icematrix.core.presentation.contract.UIState
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

data class TeamsUIState(
    val teams: List<TeamModel> = emptyList()
): UIState