package com.ggu.media.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ggu.media.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val topLevelRoutes = listOf(
        TopLevelRoute("Media", Media, R.drawable.ic_photo_library),
        TopLevelRoute("Album", Album, R.drawable.ic_folder),
        TopLevelRoute("Story", Story, R.drawable.ic_location_on),
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        topLevelRoutes.forEach { route ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = route.icon),
                        contentDescription = route.name
                    )
                },
                label = { Text(route.name) },
                selected = currentDestination?.route == route.route::class.qualifiedName,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}