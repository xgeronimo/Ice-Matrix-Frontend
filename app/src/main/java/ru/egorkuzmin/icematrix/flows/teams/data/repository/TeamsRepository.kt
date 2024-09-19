package ru.egorkuzmin.icematrix.flows.teams.data.repository

import ru.egorkuzmin.icematrix.flows.teams.data.api.TeamsApi
import ru.egorkuzmin.icematrix.flows.teams.domain.models.FavoriteTeamModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.MatchModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.PlayerModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

class TeamsRepository(
    private val api: TeamsApi,
) {

    suspend fun getTeams(): List<TeamModel> = api.teams()

    suspend fun getTeam(id: String): TeamModel = api.team(id)

    suspend fun getTeamPlayers(id: String): List<PlayerModel> = api.teamPlayers(id)

    suspend fun getMatches(id: String): List<MatchModel> = api.matches(id)

    suspend fun addToFavorites(id: String) = api.addToFavorites(FavoriteTeamModel(id))
}