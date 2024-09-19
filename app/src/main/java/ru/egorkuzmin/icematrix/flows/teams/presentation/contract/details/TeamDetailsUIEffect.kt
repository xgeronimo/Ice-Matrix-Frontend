package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

sealed class TeamDetailsUIEffect: UIEffect {
    class ShowSnackbar(val message: String): TeamDetailsUIEffect()

    class OpenTeamDetails(val team: TeamModel): TeamDetailsUIEffect()
}