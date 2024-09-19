package ru.egorkuzmin.icematrix.core.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.egorkuzmin.icematrix.core.data.repository.TokensRepository
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractor
import ru.egorkuzmin.icematrix.core.domain.interactor.TokensInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideTokensInteractor(
        repo: TokensRepository,
        sharedPreferences: SharedPreferences,
    ): TokensInteractor =
        TokensInteractorImpl(
            tokensRepository = repo,
            prefs = sharedPreferences,
        )
}