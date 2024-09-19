package ru.egorkuzmin.icematrix.flows.auth.data.repository

import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.flows.auth.data.api.AuthApi
import ru.egorkuzmin.icematrix.flows.auth.domain.models.LoginRequest
import ru.egorkuzmin.icematrix.flows.auth.domain.models.RegisterRequest

class AuthRepository(
    private val api: AuthApi,
    private val tokensInteractor: TokensInteractor,
) {

    suspend fun register(email: String, username: String, password: String) =
        api.register(
            RegisterRequest(
                email = email,
                username = username,
                password = password,
            )
        )

    suspend fun login(email: String, password: String) {
        val tokens = api.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
        tokensInteractor.accessToken = tokens.accessToken
        tokensInteractor.email = email
    }

    suspend fun getUser() = api.user()

    fun isUserAuthorized(): Boolean =
        tokensInteractor.accessToken != null

    fun logout() =
        tokensInteractor.logout()
}