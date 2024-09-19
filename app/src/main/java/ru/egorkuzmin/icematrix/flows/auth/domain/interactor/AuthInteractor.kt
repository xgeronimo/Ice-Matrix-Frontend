package ru.egorkuzmin.icematrix.flows.auth.domain.interactor

import ru.egorkuzmin.icematrix.flows.auth.domain.models.UserModel

interface AuthInteractor {

    suspend fun login(email: String, password: String)

    suspend fun register(email: String, username: String, password: String)

    suspend fun getUser(): UserModel

    fun isUserAuthorized(): Boolean

    fun logout()
}