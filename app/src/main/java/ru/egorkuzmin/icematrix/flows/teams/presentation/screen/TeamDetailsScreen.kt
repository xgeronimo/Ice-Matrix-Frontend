package ru.egorkuzmin.icematrix.flows.teams.presentation.screen

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
import ru.egorkuzmin.icematrix.flows.destinations.NewsDetailScreenDestination
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.content.NewsScreenContent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.viewmodel.NewsViewModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel
import ru.egorkuzmin.icematrix.flows.teams.presentation.content.TeamDetailsScreenContent
import ru.egorkuzmin.icematrix.flows.teams.presentation.content.TeamsScreenContent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEffect
import ru.egorkuzmin.icematrix.flows.teams.presentation.viewmodel.TeamDetailsViewModel
import ru.egorkuzmin.icematrix.flows.teams.presentation.viewmodel.TeamsViewModel


@Destination
@Composable
fun TeamDetailsScreen(
    id: String,
    viewModel: TeamDetailsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect: TeamDetailsUIEffect ->
            uiEffectHandler(effect, navigator, snackbarHostState)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(TeamDetailsUIEvent.OnScreenOpened(id))
    }

    TeamDetailsScreenContent(
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
    effect: TeamDetailsUIEffect,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState,
) {
    when(effect){
        is TeamDetailsUIEffect.ShowSnackbar ->
            snackbarHostState.showSnackbar(effect.message)

        is TeamDetailsUIEffect.OpenTeamDetails -> {}
//            navigator.navigate(NewsDetailScreenDestination(id = effect.id))
    }
}