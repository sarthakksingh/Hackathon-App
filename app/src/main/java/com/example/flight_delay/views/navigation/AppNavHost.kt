package com.example.flight_delay.views.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flight_delay.views.screens.OnboardingScreen
import com.example.flight_delay.views.screens.SearchResultScreen
import com.example.flight_delay.views.screens.SearchScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Onboarding.route
    ) {

        // 🟢 ONBOARDING
        composable(NavRoute.Onboarding.route) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(NavRoute.Search.route) {
                        popUpTo(NavRoute.Onboarding.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }


        composable(NavRoute.Search.route) {
            SearchScreen(
                onCheckDelay = {
                    navController.navigate(NavRoute.SearchResult.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }


        composable(NavRoute.SearchResult.route) {
            SearchResultScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
