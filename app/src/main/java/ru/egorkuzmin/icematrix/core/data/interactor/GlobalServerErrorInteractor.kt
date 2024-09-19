package ru.egorkuzmin.icematrix.core.data.interactor

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GlobalServerErrorInteractor {

    private val _errorFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val errorFlow: SharedFlow<Boolean>
        get() = _errorFlow

    @OptIn(DelicateCoroutinesApi::class)
    fun emitError() {
        GlobalScope.launch {
            _errorFlow.emit(true)
            _errorFlow.emit(false)
        }
    }
}