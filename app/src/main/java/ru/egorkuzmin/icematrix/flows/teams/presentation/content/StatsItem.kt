package ru.egorkuzmin.icematrix.flows.teams.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.egorkuzmin.icematrix.core.utils.conditional

@Composable
fun StatsItem(
    first: String,
    second: String,
    valueFirst: Int,
    valueSecond: Int,
) {
    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(text = "$first ($valueFirst)")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$second ($valueSecond)")
        }
        Row {
            Box(
                modifier = Modifier
                    .conditional(condition = valueFirst != 0){
                        weight(valueFirst.toFloat())
                    }
                    .height(20.dp)
                    .clip(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(color = Color(0xFF9CCC65))
            )

            Box(
                modifier = Modifier
                    .conditional(condition = valueSecond != 0){
                        weight(valueSecond.toFloat())
                    }
                    .height(20.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .background(color = Color(0xFF6C65CC))
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun StatsItemPreview() {
    StatsItem(
        "Asd",
        "Fgh",
        1,
        2
    )
}