package ru.egorkuzmin.icematrix.flows.auth.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.egorkuzmin.icematrix.core.domain.models.TokensModel
import ru.egorkuzmin.icematrix.flows.auth.domain.models.LoginRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.RegisterRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.UserModel

interface AuthApi {

    @POST("register/")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    )

    @POST("login/")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): TokensModel

    @GET("users/me")
    suspend fun user(): UserModel
}