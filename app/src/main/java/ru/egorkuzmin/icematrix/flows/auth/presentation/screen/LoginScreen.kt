package ru.egorkuzmin.icematrix.flows.auth.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import ru.egorkuzmin.icematrix.flows.auth.presentation.content.LoginScreenContent
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.login.LoginUIEffect
import ru.egorkuzmin.icematrix.flows.auth.presentation.viewmodel.LoginViewModel
import ru.egorkuzmin.icematrix.flows.destinations.LoginScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.NewsScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.ProfileScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.RegisterScreenDestination


@Destination(start = true)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect: LoginUIEffect ->
            uiEffectHandler(effect, navigator, snackbarHostState)
        }
    }

    LoginScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        uiState = state,
        onEvent = viewModel::setEvent,
        snackbarHostState = snackbarHostState,
    )
}

private suspend fun uiEffectHandler(
    effect: LoginUIEffect,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState,
) {
    when(effect){
        LoginUIEffect.OpenRegisterScreen ->
            navigator.navigate(RegisterScreenDestination) {
                popUpTo(LoginScreenDestination) {
                    inclusive = true
                }
                launchSingleTop = true
            }

        LoginUIEffect.GoBack ->
            navigator.popBackStack()

        LoginUIEffect.OpenMainScreen -> {
            navigator.navigate(NewsScreenDestination) {
                popUpTo(NewsScreenDestination)
            }
        }

        is LoginUIEffect.ShowSnackbar ->
            snackbarHostState.showSnackbar(effect.message)
    }
}