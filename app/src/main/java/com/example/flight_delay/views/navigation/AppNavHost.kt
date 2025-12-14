package com.example.flight_delay.views.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flight_delay.views.screens.OnboardingScreen
import com.example.flight_delay.views.screens.SearchResultScreen
import com.example.flight_delay.views.screens.SearchScreen
import com.example.flight_delay.vm.DelayVm

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Onboarding.route
    ) {

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
            val vm: DelayVm = hiltViewModel()
            SearchScreen(
                viewModel = vm,
                onBackClick = { navController.popBackStack()},
                onCheckDelay = {
                    navController.navigate(NavRoute.SearchResult.route)
                }
            )
        }

        composable(NavRoute.SearchResult.route) {
            val vm: DelayVm = hiltViewModel()
            SearchResultScreen(
                viewModel = vm,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
