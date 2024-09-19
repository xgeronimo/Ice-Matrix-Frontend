package ru.egorkuzmin.icematrix.flows.auth.domain.models

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
)