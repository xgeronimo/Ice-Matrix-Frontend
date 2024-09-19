package ru.egorkuzmin.icematrix.flows.news.presentation.screen

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
import ru.egorkuzmin.icematrix.flows.news.presentation.content.NewsDetailScreenContent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.content.NewsScreenContent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEffect
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.viewmodel.NewsDetailViewModel
import ru.egorkuzmin.icematrix.flows.news.presentation.viewmodel.NewsViewModel


@Destination
@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    id: Int,
) {
    val state by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect: NewsDetailUIEffect ->
            uiEffectHandler(effect, navigator, snackbarHostState)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(NewsDetailUIEvent.OnScreenOpened(id))
    }

    NewsDetailScreenContent(
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
    effect: NewsDetailUIEffect,
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState,
) {
    when (effect) {
        is NewsDetailUIEffect.ShowSnackbar ->
            snackbarHostState.showSnackbar(effect.message)
    }
}