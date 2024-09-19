package ru.egorkuzmin.icematrix.core.domain.interactor

interface TokensInteractor {

    var accessToken: String?

    var username: String?

    var email: String?

    fun logout()
    fun isTokenExpired(): Boolean
    fun updateTokens()
}