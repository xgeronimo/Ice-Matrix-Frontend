package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details

import ru.egorkuzmin.icematrix.core.presentation.contract.UIState
import ru.egorkuzmin.icematrix.flows.teams.domain.models.MatchModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.PlayerModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

data class TeamDetailsUIState(
    val team: TeamModel? = null,
    val players: List<PlayerModel> = emptyList(),
    val matches: List<MatchModel> = emptyList()
): UIState