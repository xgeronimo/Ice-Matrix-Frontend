package ru.egorkuzmin.icematrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.ramcosta.composedestinations.utils.isRouteOnBackStackAsState
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import ru.egorkuzmin.icematrix.flows.NavGraphs
import ru.egorkuzmin.icematrix.flows.destinations.LoginScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.NewsScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.ProfileScreenDestination
import ru.egorkuzmin.icematrix.flows.destinations.TeamsScreenDestination
import ru.egorkuzmin.icematrix.main.models.BottomBarItem
import ru.egorkuzmin.icematrix.ui.theme.IceMatrixTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val viewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      IceMatrixTheme {

        val navController = rememberNavController()
        val context = LocalContext.current
        var navigationSelectedItem by remember {
          mutableIntStateOf(0)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val start = if (viewModel.isAuthorized) NewsScreenDestination else LoginScreenDestination

        Scaffold(
          modifier = Modifier.fillMaxSize(),
          bottomBar = {
            AnimatedVisibility(
              visible = navBackStackEntry.isBottomNavigationBarVisible(),
              enter = slideInVertically(initialOffsetY = { it }),
              exit = slideOutVertically(targetOffsetY = { it })
            ) {
              BottomBar(navController)
            }
          }
        ) {
          DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier
              .padding(it)
              .fillMaxSize(),
            startRoute = start
          )
        }
      }
    }
  }

  @Composable
  fun BottomBar(
    navController: NavHostController
  ) {
    val navigator = navController.rememberDestinationsNavigator()

    NavigationBar {
      BottomBarItem.entries.forEach { destination ->
          val isCurrentDestOnBackStack by navController.isRouteOnBackStackAsState(destination.direction)
          NavigationBarItem(
            selected = isCurrentDestOnBackStack,
            label = {
              Text(destination.label)
            },
            icon = {
              Icon(
                destination.icon,
                contentDescription = destination.label
              )
            },
            onClick = {
              if (isCurrentDestOnBackStack) {
                // When we click again on a bottom bar item and it was already selected
                // we want to pop the back stack until the initial destination of this bottom bar item
                navigator.popBackStack(destination.direction, false)
                return@NavigationBarItem
              }

              navigator.navigate(destination.direction) {
                // Pop up to the root of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(NavGraphs.root) {
                  saveState = true
                }

                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
              }
            },
          )
        }
    }
  }
}

@Composable
private fun ShowLoginWhenLoggedOut(
  viewModel: MainViewModel,
  navController: NavHostController
) {
  val currentDestination by navController.currentDestinationAsState()
  val navigator = navController.rememberDestinationsNavigator()

  if (currentDestination != LoginScreenDestination) {
    // everytime destination changes or logged in state we check
    // if we have to show Login screen and navigate to it if so
    navigator.navigate(LoginScreenDestination) {
      launchSingleTop = true
    }
  }
}

private fun NavBackStackEntry?.isBottomNavigationBarVisible(): Boolean {
  val screenVisible = listOf(
    NewsScreenDestination.route,
    ProfileScreenDestination.route,
    TeamsScreenDestination.route,
  )
  return this?.destination?.route in screenVisible
}