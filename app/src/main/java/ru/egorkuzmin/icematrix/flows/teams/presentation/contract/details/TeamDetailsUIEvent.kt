package ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details

import ru.egorkuzmin.icematrix.core.presentation.contract.UIEvent
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

sealed class TeamDetailsUIEvent: UIEvent {
    class OnScreenOpened(val id: String): TeamDetailsUIEvent()
    class OnPlayerClicked(val id: Int): TeamDetailsUIEvent()
    class OnAddToFavorites(val id: String): TeamDetailsUIEvent()
}