package com.englesoft.weatherinfo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.englesoft.weatherinfo.presentation.feature.home.HomeScreen
import com.englesoft.weatherinfo.presentation.feature.search.SearchScreen
import com.englesoft.weatherinfo.presentation.feature.home.HomeViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToSearch = { navController.navigate("search") }
            )
        }
        composable("search") {
            SearchScreen(
                onZilaSelected = { selectedZila ->
                    homeViewModel.updateWeatherForSelectedZila(selectedZila)
                    navController.popBackStack()
                },
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}
