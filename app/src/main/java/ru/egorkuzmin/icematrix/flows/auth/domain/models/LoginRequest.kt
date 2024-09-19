package ru.egorkuzmin.icematrix.flows.auth.domain.models

data class LoginRequest(
    val email: String,
    val password: String,
)