package ru.egorkuzmin.icematrix.flows.teams.domain.interactor

import ru.egorkuzmin.icematrix.flows.teams.data.repository.TeamsRepository
import ru.egorkuzmin.icematrix.flows.teams.domain.models.MatchModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.PlayerModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel
import javax.inject.Inject

class TeamsInteractor @Inject constructor(
    private val repository: TeamsRepository,
) {
    suspend fun getTeams(): List<TeamModel> =
        repository.getTeams()

    suspend fun getTeam(id: String): TeamModel =
        repository.getTeam(id)

    suspend fun getTeamsPlayers(id: String): List<PlayerModel> =
        repository.getTeamPlayers(id)

    suspend fun getMatches(id: String): List<MatchModel> =
        repository.getMatches(id)

    suspend fun addToFavorites(id: String) =
        repository.addToFavorites(id)
}