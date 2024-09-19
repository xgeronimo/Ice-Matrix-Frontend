package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ExpandableCard(
    content: @Composable () -> Unit,
    hideContent: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .animateContentSize(),
            onClick = { expanded = !expanded }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                content()

                if (expanded) {
                    hideContent()
                }
            }
        }
    }
}