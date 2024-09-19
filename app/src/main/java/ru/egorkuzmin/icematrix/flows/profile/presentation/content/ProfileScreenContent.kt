package ru.egorkuzmin.icematrix.flows.profile.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.egorkuzmin.icematrix.R
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIEvent
import ru.egorkuzmin.icematrix.flows.profile.presentation.contract.ProfileUIState

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    onEvent: (ProfileUIEvent) -> Unit,
    uiState: ProfileUIState,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->
        Box {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = "Placeholder",
                ) }
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(top = 160.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "User avatar"
                    )

                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Логин: ",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = uiState.username ?: "",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "EMAIL: ", fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = uiState.email ?: "",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Любимая команда: ", fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = uiState.favoriteTeamId ?: "пока не выбрали",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                Spacer(Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .padding(28.dp)
                        .fillMaxWidth(),
                    onClick = { onEvent(ProfileUIEvent.OnLogoutClicked) },
                    enabled = true,
                    shape = RoundedCornerShape(15.dp),
                ) {
                    Text("Выйти")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileScreen() {
    ProfileScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = ProfileUIState("name", "email", "KHL"),
        onEvent = {},
        snackbarHostState = SnackbarHostState(),
    )
}