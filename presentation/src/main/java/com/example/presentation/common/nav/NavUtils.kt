package com.example.presentation.common.nav

import androidx.navigation.NavController

fun NavController.safePopSBackStack() {
    if (this.previousBackStackEntry != null) {
        this.popBackStack()
    }
}

fun NavController.safeNavigate(route: String) {
    if (this.currentDestination?.route != route) {
        this.navigate(route) {
            launchSingleTop = true
            popUpTo(route) {
                inclusive = false
            }
        }
    }
}