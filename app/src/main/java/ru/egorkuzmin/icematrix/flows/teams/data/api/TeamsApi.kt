package ru.egorkuzmin.icematrix.flows.teams.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import ru.egorkuzmin.icematrix.core.domain.models.TokensModel
import ru.egorkuzmin.icematrix.core.network.annotations.AuthInterceptor
import ru.egorkuzmin.icematrix.core.network.annotations.NeedAuthentication
import ru.egorkuzmin.icematrix.flows.auth.domain.models.LoginRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.RegisterRequest
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.FavoriteTeamModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.MatchModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.PlayerModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel

interface TeamsApi {

    @GET("teams/")
    suspend fun teams(): List<TeamModel>

    @GET("teams/{id}")
    suspend fun team(@Path("id") id: String): TeamModel

    @GET("teams/{id}/players")
    suspend fun teamPlayers(@Path("id") id: String): List<PlayerModel>

    @GET("matches/team/{id}")
    suspend fun matches(@Path("id") id: String): List<MatchModel>

    @NeedAuthentication
    @PATCH("users/me")
    suspend fun addToFavorites(@Body body: FavoriteTeamModel)
}