package ru.egorkuzmin.icematrix.flows.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.egorkuzmin.icematrix.flows.auth.data.repository.AuthRepository
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideAuthInteractor(
        repo: AuthRepository,
    ): AuthInteractor =
        AuthInteractorImpl(
            repository = repo,
        )
}