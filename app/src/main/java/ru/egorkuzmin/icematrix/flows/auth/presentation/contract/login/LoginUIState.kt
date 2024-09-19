package ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login

import android.util.Patterns
import ru.egorkuzmin.icematrix.core.presentation.contract.UIState
import java.util.regex.Pattern


data class LoginUIState(
    val emailOrUsername: String = "",
    val password: String = "",
) : UIState {

    private val isEmail = emailOrUsername.contains('@')

    val isButtonEnabled = (emailOrUsername.isNotBlank() && password.isNotBlank())
                && (!isEmail || isValidEmail(emailOrUsername))

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}