package com.melyseev.seasonticket72

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.melyseev.seasonticket72.main.MainBottomScreen
import com.melyseev.seasonticket72.tabs.seasonTicketFlow
import com.melyseev.seasonticket72.tabs.sellTicketFlow
import com.melyseev.seasonticket72.tabs.userListFlow
import com.melyseev.seasonticket72.theme.*
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val isDarkModeValue = false // isSystemInDarkTheme()

            val currentStyle = remember { mutableStateOf(JetDanceStyle.Purple) }
            val currentFontSize = remember { mutableStateOf(JetDanceSize.Medium) }
            val currentPaddingSize = remember { mutableStateOf(JetDanceSize.Medium) }
            val currentCornersStyle = remember { mutableStateOf(JetDanceCorners.Rounded) }
            val isDarkMode = remember { mutableStateOf(isDarkModeValue) }



            val items = listOf(
                MainBottomScreen.UserList,
                MainBottomScreen.SeasonTicket,
                MainBottomScreen.SellTicket,
            )
            MainTheme(
                style = currentStyle.value,
                darkTheme = isDarkMode.value,
                textSize = currentFontSize.value,
                corners = currentCornersStyle.value,
                paddingSize = currentPaddingSize.value
            ) {

                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()

                // Set status bar color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (isDarkMode.value) baseDarkPalette.primaryBackground else baseLightPalette.primaryBackground,
                        darkIcons = !isDarkMode.value
                    )
                }

                Surface {
                    Column {
                        Box(modifier = Modifier.weight(1f)) {


                            NavHost(
                                navController = navController,
                                startDestination = MainBottomScreen.SeasonTicket.route //MainBottomScreen.UserList.route
                            ) {

                                userListFlow(navController = navController)
                                seasonTicketFlow(navController = navController)
                                sellTicketFlow(navController = navController)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .height(56.dp)
                                .fillMaxWidth()
                        ) {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach { screen ->
                                    val isSelected = currentDestination?.hierarchy
                                        ?.any { it.route == screen.route } == true

                                    BottomNavigationItem(
                                        modifier = Modifier.background(JetDanceTheme.colors.primaryBackground),
                                        icon = {
                                            Icon(
                                                imageVector = when (screen) {
                                                    MainBottomScreen.UserList -> Icons.Filled.Favorite
                                                    MainBottomScreen.SeasonTicket -> Icons.Filled.Settings
                                                    MainBottomScreen.SellTicket -> Icons.Filled.Sell
                                                },
                                                contentDescription = null,
                                                tint = if (isSelected) JetDanceTheme.colors.tintColor else JetDanceTheme.colors.controlColor
                                            )
                                        },
                                        label = { Text(
                                            stringResource(screen.resourceId),
                                            color = if (isSelected) JetDanceTheme.colors.primaryText else JetDanceTheme.colors.controlColor) },
                                        selected = isSelected,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items

                                                /*
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        */

                                                popUpTo(navController.graph.id) {
                                                    saveState = true
                                                }

                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true


                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }

    }
}