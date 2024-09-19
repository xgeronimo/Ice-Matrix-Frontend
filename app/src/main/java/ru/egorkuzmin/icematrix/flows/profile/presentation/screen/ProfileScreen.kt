package ru.egorkuzmin.icematrix.flows.profile.presentation.screen

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import ru.egorkuzmin.icematrix.flows.destinations.LoginScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.ProfileScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.RegisterScreenDestination
import ru.egorkuzmin.icematrix.flows.profile.presentation.content.ProfileScreenContent
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEffect
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEvent
import ru.egorkuzmin.icematrix.flows.profile.presentation.viewmodel.ProfileViewModel


@Destination
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect: ProfileUIEffect ->
            uiEffectHandler(effect, navigator, snackbarHostState)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(ProfileUIEvent.OnScreenOpened)
    }

    ProfileScreenContent(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        onEvent = viewModel::setEvent,
        uiState = state,
        snackbarHostState = snackbarHostState,
    )
}

private suspend fun uiEffectHandler(
    effect: ProfileUIEffect,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState,
) {
    when(effect){
        is ProfileUIEffect.ShowSnackbar ->
            snackbarHostState.showSnackbar(effect.message)

        ProfileUIEffect.Logout ->
            navigator.navigate(LoginScreenDestination) {
                popUpTo(ProfileScreenDestination) {
                    inclusive = true
                }
            }
    }
}