package ru.egorkuzmin.icematrix.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.core.network.annotations.NeedAuthentication
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
    private val tokensInteractor: TokensInteractor,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val annotation = findAnnotation(request)

        if (annotation != null && tokensInteractor.isTokenExpired()) {
//            tokensInteractor.updateTokens()
            tokensInteractor.logout()
        }

        if (tokensInteractor.accessToken != null) {
            request = chain.request().newBuilder()
                .header(
                    "Authorization", "Bearer ${tokensInteractor.accessToken}"
                )
                .build()
        }

        return chain.proceed(request)
    }

    private fun findAnnotation(
        request: Request,
    ): NeedAuthentication? =
        request.tag(Invocation::class.java)
            ?.method()
            ?.annotations
            ?.filterIsInstance<NeedAuthentication>()
            ?.firstOrNull()
}