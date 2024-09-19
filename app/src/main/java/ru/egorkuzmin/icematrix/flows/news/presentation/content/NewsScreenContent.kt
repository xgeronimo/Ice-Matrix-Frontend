package ru.egorkuzmin.icematrix.flows.news.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIState

@Composable
fun NewsScreenContent(
    modifier: Modifier = Modifier,
    uiState: NewsUIState,
    onEvent: (NewsUIEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        uiState.news.forEach { article ->
            NewsCardContent(
                id = article.id,
                title = article.title,
                subtitle = article.content,
                onEvent = onEvent,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    NewsScreenContent(
        uiState = NewsUIState(),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}