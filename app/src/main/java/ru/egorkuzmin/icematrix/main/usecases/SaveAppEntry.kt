package ru.egorkuzmin.icematrix.main.usecases

import ru.egorkuzmin.icematrix.main.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}