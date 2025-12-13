package com.example.flight_delay.views.navigation

sealed class NavRoute(val route: String) {
    object Onboarding : NavRoute("onboarding")
    object Search : NavRoute("search")
    object SearchResult : NavRoute("search_result")
}
