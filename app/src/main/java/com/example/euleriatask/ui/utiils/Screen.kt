package com.example.euleriatask.ui.utiils

sealed class Screen(val route: String) {
    data object SelectDurationScreen : Screen(Utils.FIRST_SCREEN_ROUTE)
    data object MonitoringScreen : Screen(Utils.MONITORING_SCREEN_ROUTE)
}