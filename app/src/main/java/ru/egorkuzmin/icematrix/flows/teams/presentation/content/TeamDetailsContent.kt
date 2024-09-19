package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIState
import ru.egorkuzmin.icematrix.ui.theme.IceMatrixTheme

@Composable
fun TeamDetailsScreenContent(
    modifier: Modifier = Modifier,
    uiState: TeamDetailsUIState,
    onEvent: (TeamDetailsUIEvent) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Box {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Placeholder",
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 160.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = uiState.team?.name ?: "",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Тренер: " + (uiState.team?.trainer ?: ""),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
            )

            uiState.players.forEach { player ->
                PlayerContent(
                    player = player,
                )
            }

            uiState.matches.forEach { match ->
                ExpandableCard(
                    content = {
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "${match.firstTeam.name} против ${match.secondTeam.name}"
                        )
                    },
                    hideContent = {
                        if (match.scoreTeam1 != 0 || match.scoreTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Голы",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.scoreTeam1,
                                match.scoreTeam1,
                            )
                        }
                        if (match.majorPucksTeam1 != 0 || match.majorPucksTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Шайбы в большинстве",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.majorPucksTeam1,
                                match.majorPucksTeam2,
                            )
                        }

                        if (match.advantagesTeam1 != 0 || match.advantagesTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Численное преимущество",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.advantagesTeam1,
                                match.advantagesTeam2,
                            )
                        }

                        if (match.faceoffsWonTeam1 != 0 || match.faceoffsWonTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Выигранные вбрасывания",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.faceoffsWonTeam1,
                                match.faceoffsWonTeam2,
                            )
                        }

                        if (match.penaltyMinutesTeam1 != 0 || match.penaltyMinutesTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Выигранные вбрасывания",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.penaltyMinutesTeam1,
                                match.penaltyMinutesTeam2,
                            )
                        }

                        if (match.shotsOnGoalTeam1 != 0 || match.shotsOnGoalTeam2 != 0) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp), text = "Броски по воротам",
                                fontWeight = FontWeight.Bold
                            )
                            StatsItem(
                                match.firstTeam.name,
                                match.secondTeam.name,
                                match.shotsOnGoalTeam1,
                                match.shotsOnGoalTeam2,
                            )
                        }
                    }
                )
            }

            Button(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                onClick = { onEvent(TeamDetailsUIEvent.OnAddToFavorites(uiState.team?.id ?: "")) },
                enabled = true,
                shape = RoundedCornerShape(15.dp),
            ) {
                Text("Добавить в любимую команду")
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTeamDetailsScreenContent() {
    TeamDetailsScreenContent(
        uiState = TeamDetailsUIState(),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}