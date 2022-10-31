package edu.ucne.articulosapi

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.articulosapi.ui.articulo.EditArticuloScreen
import edu.ucne.articulosapi.ui.articulo.HomeArticuloScreen
import edu.ucne.articulosapi.ui.menu.PrincipalScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.PrincipalScreen.route
    ) {
        composable(route = Screen.PrincipalScreen.route) {
            PrincipalScreen(navController = navController)
        }

        composable(route = Screen.HomeArticulo.route) {
            HomeArticuloScreen(navController = navController)
        }
        composable(
            route = Screen.EditArticulo.route,
            arguments = listOf(
                navArgument(
                    name = "ariticuloId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            EditArticuloScreen(navController = navController)
        }

    }
}