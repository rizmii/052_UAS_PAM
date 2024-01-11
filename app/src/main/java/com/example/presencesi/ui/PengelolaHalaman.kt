package com.example.presencesi.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presencesi.ui.add.AddScreen
import com.example.presencesi.ui.add.DestinasiAdd
import com.example.presencesi.ui.edit.EditDestination
import com.example.presencesi.ui.edit.EditScreen
import com.example.presencesi.ui.history.DetailDestination
import com.example.presencesi.ui.history.HistoryScreen
import com.example.presencesi.ui.home.DestinasiHome
import com.example.presencesi.ui.home.HomeScreen

@Composable
fun PresensiApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier,

    ) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiAdd.route) },
                onDetailClick = {
                    navController.navigate("${DetailDestination.route}/$it")
                },
            )
        }
        composable(DestinasiAdd.route) {
            AddScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(
            DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.presensiId)
            {
                type = NavType.IntType
            })) {
            HistoryScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = {
                    navController.navigate("${EditDestination.route}/$it")
                }
            )
        }
        composable(
            EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.presensiId)
            {
                type = NavType.IntType
            })) {
            EditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = {navController.navigateUp()
                }
            )
        }

    }

}