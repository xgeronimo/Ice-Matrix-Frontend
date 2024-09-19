package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

sealed class TeamsUIEvent: UIEvent {

    class OnTeamClicked(val team: TeamModel): TeamsUIEvent()
}