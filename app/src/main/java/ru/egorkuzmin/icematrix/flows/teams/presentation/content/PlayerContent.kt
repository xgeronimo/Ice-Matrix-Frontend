package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ru.egorkuzmin.icematrix.flows.teams.domain.models.PlayerModel
import ru.egorkuzmin.icematrix.flows.teams.domain.models.TeamModel
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.details.TeamDetailsUIEvent
import ru.egorkuzmin.icematrix.flows.teams.presentation.contract.teams.TeamsUIEvent

@Composable
fun PlayerContent(
    player: PlayerModel
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = { expanded = !expanded },
        content = {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = player.name,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = player.position,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp),
                            fontSize = 16.sp,
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Image(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(64.dp)
                            .clip(CircleShape)
                            .size(26.dp)
                    )
                }

                if(expanded) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row {
                            Text(text = "Рост: ", fontWeight = FontWeight.Bold)
                            Text(text = player.height.toString())
                        }
                        Row {
                            Text(text = "Вес: ", fontWeight = FontWeight.Bold)
                            Text(text = player.weight.toString())
                        }
                        Row {
                            Text(text = "Номер: ", fontWeight = FontWeight.Bold)
                            Text(text = player.number.toString())
                        }
                        Row {
                            Text(text = "Дата рождения: ", fontWeight = FontWeight.Bold)
                            Text(text = player.dateOfBirth)
                        }
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun TeamCardPreview() {
    PlayerContent(
        player = PlayerModel(
            id = 2,
            name = "Иван Иванов",
            dateOfBirth = "12.12.12",
            position = "position",
            weight = 90,
            height = 90,
            number = 10,
        ),
    )
}