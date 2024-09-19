package ru.egorkuzmin.icematrix.flows.news.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.egorkuzmin.icematrix.R
import ru.egorkuzmin.icematrix.flows.news.domain.models.NewsModel
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIState
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIEvent
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.newsdetail.NewsDetailUIState

@Composable
fun NewsDetailScreenContent(
    modifier: Modifier = Modifier,
    uiState: NewsDetailUIState,
    onEvent: (NewsDetailUIEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(painter = painterResource(id = R.drawable.hockey), contentDescription = "Placeholder")
        HorizontalDivider()
        Column {
            Text(
                text = uiState.news?.title ?: "Заголовок новости",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.news?.content ?: "Тут пока ничего нет",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewsDetailScreenContent() {
    NewsDetailScreenContent(
        uiState = NewsDetailUIState(
            news = NewsModel(2, "Title", "Subtitle")
        ),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}