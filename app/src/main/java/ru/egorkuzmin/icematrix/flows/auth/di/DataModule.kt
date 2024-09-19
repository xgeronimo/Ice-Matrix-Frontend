package ru.egorkuzmin.icematrix.flows.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.egorkuzmin.icematrix.flows.auth.data.api.AuthApi
import ru.egorkuzmin.icematrix.flows.auth.data.repository.AuthRepository
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideAuthRepository(
        api: AuthApi,
        tokensInteractor: TokensInteractor,
    ): AuthRepository =
        AuthRepository(
            api = api,
            tokensInteractor = tokensInteractor,
        )
}