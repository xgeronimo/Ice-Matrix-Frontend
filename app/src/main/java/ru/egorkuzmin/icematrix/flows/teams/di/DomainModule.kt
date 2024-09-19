package ru.egorkuzmin.icematrix.flows.teams.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.egorkuzmin.icematrix.flows.teams.data.repository.TeamsRepository
import ru.egorkuzmin.icematrix.flows.teams.domain.interactor.TeamsInteractor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideTeamsInteractor(
        repo: TeamsRepository,
    ): TeamsInteractor =
        TeamsInteractor(
            repository = repo,
        )
}