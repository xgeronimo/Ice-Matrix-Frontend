package ru.egorkuzmin.icematrix.flows.auth.domain.interactor

import ru.egorkuzmin.icematrix.flows.auth.data.repository.AuthRepository
import ru.egorkuzmin.icematrix.flows.auth.domain.models.UserModel

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {
    override suspend fun login(email: String, password: String) =
        repository.login(email, password)

    override suspend fun register(email: String, username: String, password: String) =
        repository.register(email, username, password)

    override fun isUserAuthorized(): Boolean =
        repository.isUserAuthorized()

    override fun logout() {
        repository.logout()
    }

    override suspend fun getUser(): UserModel =
        repository.getUser()
}