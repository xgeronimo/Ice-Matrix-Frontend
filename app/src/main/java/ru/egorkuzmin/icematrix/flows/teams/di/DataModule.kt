package ru.egorkuzmin.icematrix.flows.teams.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.egorkuzmin.icematrix.flows.auth.data.api.AuthApi
import ru.egorkuzmin.icematrix.flows.auth.data.repository.AuthRepository
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.flows.news.data.api.NewsApi
import ru.egorkuzmin.icematrix.flows.news.data.repository.NewsRepository
import ru.egorkuzmin.icematrix.flows.teams.data.api.TeamsApi
import ru.egorkuzmin.icematrix.flows.teams.data.repository.TeamsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideTeamsApiService(retrofit: Retrofit): TeamsApi =
        retrofit.create(TeamsApi::class.java)

    @Singleton
    @Provides
    fun provideTeamsRepository(
        api: TeamsApi,
    ): TeamsRepository =
        TeamsRepository(
            api = api,
        )
}