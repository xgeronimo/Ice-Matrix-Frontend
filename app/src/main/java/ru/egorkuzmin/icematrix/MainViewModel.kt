package ru.egorkuzmin.icematrix

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.egorkuzmin.icematrix.flows.auth.domain.interactor.AuthInteractor
import ru.egorkuzmin.icematrix.flows.destinations.LoginScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.NewsScreenDestination
import ru.egorkuzmin.icematrix.main.usecases.AppEntryUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
): ViewModel() {

    var isAuthorized = authInteractor.isUserAuthorized()
}