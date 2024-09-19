package ru.egorkuzmin.icematrix.main.usecases

import kotlinx.coroutines.flow.Flow
import ru.egorkuzmin.icematrix.main.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> =
        localUserManager.readAppEntry()
}