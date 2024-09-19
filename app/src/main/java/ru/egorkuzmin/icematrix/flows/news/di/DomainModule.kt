package ru.egorkuzmin.icematrix.flows.news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.egorkuzmin.icematrix.flows.auth.data.repository.AuthRepository
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractorImpl
import ru.egorkuzmin.icematrix.flows.news.data.repository.NewsRepository
import ru.egorkuzmin.icematrix.flows.news.domain.interactor.NewsInteractor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideNewsInteractor(
        repo: NewsRepository,
    ): NewsInteractor =
        NewsInteractor(
            repository = repo,
        )
}