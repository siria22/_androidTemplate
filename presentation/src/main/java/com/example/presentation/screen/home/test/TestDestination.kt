package com.example.presentation.screen.home.test

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.common.nav.ScreenDestinations

fun NavGraphBuilder.testDestination(navController: NavController) {
    composable(
        route = ScreenDestinations.Home.Test.route
    ) {
        val viewModel: TestViewModel = hiltViewModel()

        val argument: TestArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            TestArgument(
                state = state,
                intent = viewModel::onIntent,
                event = viewModel.eventFlow
            )
        }

        TestScreen(
            navController = navController,
            argument = argument
        )
    }
}