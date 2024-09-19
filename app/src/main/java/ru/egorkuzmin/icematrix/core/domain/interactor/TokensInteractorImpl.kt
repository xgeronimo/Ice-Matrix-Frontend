package ru.egorkuzmin.icematrix.core.domain.interactor

import android.content.SharedPreferences
import ru.egorkuzmin.icematrix.core.data.repository.TokensRepository

class TokensInteractorImpl(
    private val tokensRepository: TokensRepository,
    private val prefs: SharedPreferences
) : TokensInteractor {

    override var accessToken: String?
        get() = prefs.getString(ACCESS_TOKEN, null)
        set(value) {
            prefs.edit().putString(ACCESS_TOKEN, value).apply()
        }

    override var username: String?
        get() = prefs.getString(USERNAME, null)
        set(value) {
            prefs.edit().putString(USERNAME, value).apply()
        }

    override var email: String?
        get() = prefs.getString(EMAIL, null)
        set(value) {
            prefs.edit().putString(EMAIL, value).apply()
        }

    override fun logout() {
        prefs.edit().clear().apply()
    }

    override fun isTokenExpired(): Boolean =
        false

    override fun updateTokens() { }

    companion object {

        const val ACCESS_TOKEN = "access_token"
        const val USERNAME = "username"
        const val EMAIL = "email"
    }
}