package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.egorkuzmin.icematrix.flows.news.presentation.content.NewsCardContent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIState
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIState

@Composable
fun TeamsScreenContent(
    modifier: Modifier = Modifier,
    uiState: TeamsUIState,
    onEvent: (TeamsUIEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        uiState.teams.forEach { team ->
            TeamContent(
                team = team,
                onEvent = onEvent,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    TeamsScreenContent(
        uiState = TeamsUIState(),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}