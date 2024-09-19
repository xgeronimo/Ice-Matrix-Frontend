package ru.egorkuzmin.icematrix.core.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.egorkuzmin.icematrix.core.domain.models.AccessTokenModel
import ru.egorkuzmin.icematrix.core.domain.models.RefreshTokenModel
import ru.egorkuzmin.icematrix.core.domain.models.TokensModel

interface TokensApi {

    @POST("api/token/verify/")
    suspend fun verifyToken(
        @Body accessTokenModel: AccessTokenModel
    ): Response<Unit>

    @POST("api/token/refresh/")
    suspend fun refreshToken(
        @Body refreshTokenModel: RefreshTokenModel
    ): TokensModel
}