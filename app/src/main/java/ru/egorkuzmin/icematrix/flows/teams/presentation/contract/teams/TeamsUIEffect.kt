package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

sealed class TeamsUIEffect: UIEffect {
    class ShowSnackbar(val message: String): TeamsUIEffect()

    class OpenTeamDetails(val team: TeamModel): TeamsUIEffect()
}