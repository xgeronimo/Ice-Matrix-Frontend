package ru.egorkuzmin.icematrix.main.models

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import ru.egorkuzmin.icematrix.flows.destinations.NewsScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.ProfileScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.TeamsScreenDestination

enum class BottomBarItem(
    val label: String = "",
    val icon: ImageVector = Filled.Home,
    val direction: DirectionDestinationSpec,
) {
    News(
        label = "Новости",
        icon = Filled.Home,
        direction = NewsScreenDestination,
    ),

    Teams(
        label = "Команды",
        icon = Filled.CoPresent,
        direction = TeamsScreenDestination,
    ),

    Profile(
        label = "Профиль",
        icon = Filled.AccountCircle,
        direction = ProfileScreenDestination
    )
}