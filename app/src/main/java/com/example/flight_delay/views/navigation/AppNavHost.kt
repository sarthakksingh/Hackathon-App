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
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = NavRoute.Onboarding.route
    ) {

        composable(
            route = NavRoute.Onboarding.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300)) { -it } },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300)) { -it } },
            popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) { it } },
            popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) { it } },
        ) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(NavRoute.Search.route) {
                        popUpTo(NavRoute.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = NavRoute.Search.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300)) { it } },   // from right
            exitTransition = { slideOutHorizontally(animationSpec = tween(300)) { -it } }, // to left
            popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) { -it } }, // back from left
            popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) { it } },  // to right
        ) {
            val vm: DelayVm = hiltViewModel()
            SearchScreen(
                viewModel = vm,
                onBackClick = { navController.popBackStack() },
                onCheckDelay = {
                    navController.navigate(NavRoute.SearchResult.route)
                }
            )
        }

        composable(
            route = NavRoute.SearchResult.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300)) { it } },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300)) { -it } },
            popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) { -it } },
            popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) { it } },
        ) {
            val vm: DelayVm = hiltViewModel(
                navController.getBackStackEntry(NavRoute.Search.route)
            )
            SearchResultScreen(
                viewModel = vm,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
