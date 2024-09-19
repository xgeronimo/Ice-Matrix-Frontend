package ru.egorkuzmin.icematrix.core.network.annotations

import javax.inject.Qualifier

annotation class NeedAuthentication

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor