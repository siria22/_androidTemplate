package com.example.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.common.nav.ScreenDestinations

fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable(
        route = ScreenDestinations.Home.route
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        val argument: HomeArgument = let {
            val state by viewModel.state.collectAsStateWithLifecycle()

            HomeArgument(
                state = state,
                intent = viewModel::onIntent,
                event = viewModel.eventFlow
            )
        }

        val data: HomeData = let {
            val examples by viewModel.examples.collectAsStateWithLifecycle()
            val randomUser by viewModel.randomUser.collectAsStateWithLifecycle()

            HomeData(
                examples = examples,
                randomUser = randomUser
            )
        }

        HomeScreen(
            navController = navController,
            argument = argument,
            data = data
        )
    }
}