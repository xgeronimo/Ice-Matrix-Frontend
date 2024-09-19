package ru.egorkuzmin.icematrix.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.egorkuzmin.icematrix.main.manager.LocalUserManager
import ru.egorkuzmin.icematrix.main.manager.LocalUserManagerImpl
import ru.egorkuzmin.icematrix.main.usecases.AppEntryUseCases
import ru.egorkuzmin.icematrix.main.usecases.ReadAppEntry
import ru.egorkuzmin.icematrix.main.usecases.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager,
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )
}