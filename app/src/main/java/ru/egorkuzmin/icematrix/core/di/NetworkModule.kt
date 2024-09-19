package ru.egorkuzmin.icematrix.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.egorkuzmin.icematrix.core.data.interactor.GlobalServerErrorInteractor
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.core.network.annotations.AuthInterceptor
import ru.egorkuzmin.icematrix.core.network.interceptor.AuthenticationInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @AuthInterceptor authInterceptor: Interceptor,
        globalServerErrorInteractor: GlobalServerErrorInteractor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(authInterceptor)
            .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(object : Interceptor {

                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val response = chain.proceed(request)

                    if (response.code == 500) {
                        globalServerErrorInteractor.emitError()
                        return response
                    }

                    return response
                }
            })
            .build()


    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLogger = HttpLoggingInterceptor.Logger { message ->
            Timber
                .tag(LOG_TAG)
                .d(message)
        }
        val loggingInterceptor = HttpLoggingInterceptor(httpLogger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideMoshi(
    ): Moshi {
        val moshiBuilder = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
        return moshiBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://192.168.1.121:8080/")
            .addConverterFactory(converterFactory)
            .build()

    @AuthInterceptor
    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(
        tokensInteractor: TokensInteractor,
    ): Interceptor =
        AuthenticationInterceptor(
            tokensInteractor = tokensInteractor
        )

    companion object {

        private const val LOG_TAG = "OkHttp"
        private const val READ_TIMEOUT_IN_SECONDS = 60L
        private const val CONNECTION_TIMEOUT_IN_SECONDS = 60L
    }
}