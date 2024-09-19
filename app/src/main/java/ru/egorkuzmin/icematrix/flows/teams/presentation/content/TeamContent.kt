package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.egorkuzmin.icematrix.R
import ru.egorkuzmin.icematrix.flows.news.presentation.contract.news.NewsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEvent

@Composable
fun TeamContent(
    team: TeamModel,
    onEvent: (TeamsUIEvent) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            onEvent(TeamsUIEvent.OnTeamClicked(team))
        },
        content = {
            HorizontalDivider()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = team.name,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        VerticalDivider(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .height(26.dp)
                        )
                        Text(
                            text = team.trainer,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = team.city,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.hockey),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun TeamCardPreview() {
    TeamContent(
        team = TeamModel("", "КХЛ", "Moscow", "Иванов"),
        onEvent = {},
    )
}