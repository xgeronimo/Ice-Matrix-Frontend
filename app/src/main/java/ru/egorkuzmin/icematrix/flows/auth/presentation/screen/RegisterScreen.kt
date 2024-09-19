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
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import ru.egorkuzmin.icematrix.flows.auth.AuthScreenFeature
import ru.egorkuzmin.icematrix.flows.auth.presentation.content.RegisterScreenContent
import ru.egorkuzmin.icematrix.flows.auth.presentation.contract.register.RegisterUIEffect
import ru.egorkuzmin.icematrix.flows.auth.presentation.viewmodel.RegisterViewModel
import ru.egorkuzmin.icematrix.flows.destinations.LoginScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.RegisterScreenDestination

@Destination
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect: RegisterUIEffect ->
            uiEffectHandler(effect, navigator, snackbarHostState)
        }
    }

    RegisterScreenContent(
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
    effect: RegisterUIEffect,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState,
) {
    when(effect){
        RegisterUIEffect.OpenLoginScreen ->
            navigator.navigate(LoginScreenDestination) {
                popUpTo(RegisterScreenDestination) {
                    inclusive = true
                }
                launchSingleTop = true
            }

        RegisterUIEffect.GoBack ->
            navigator.popBackStack()

        is RegisterUIEffect.ShowSnackbar ->
            snackbarHostState.showSnackbar(effect.message)
    }
}