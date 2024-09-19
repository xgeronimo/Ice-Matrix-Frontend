package ru.egorkuzmin.icematrix.flows.news.di

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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(
        api: NewsApi,
    ): NewsRepository =
        NewsRepository(
            api = api,
        )
}