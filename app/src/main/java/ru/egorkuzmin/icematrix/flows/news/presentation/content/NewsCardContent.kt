package ru.egorkuzmin.icematrix.flows.news.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
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
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent

@Composable
fun NewsCardContent(
    id: Int,
    title: String,
    subtitle: String,
    onEvent: (NewsUIEvent) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            onEvent(NewsUIEvent.OnCardClicked(id))
        },
        content = {
            Image(painter = painterResource(id = R.drawable.hockey), contentDescription = "Placeholder")
            HorizontalDivider()
            Column {
                Text(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun NewsCardPreview() {
    NewsCardContent(2,"Title", "Content Content Content " +
            "Content Content Content Content Content" +
            "Content Content Content Content Content"
    ) {}
}