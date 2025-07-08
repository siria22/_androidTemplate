package com.example.presentation.screen.home.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.common.ui.component.AppTopBar
import com.example.presentation.common.ui.theme.SiriaTemplateTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun TestScreen(
    navController: NavController,
    argument: TestArgument,
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppTopBar(
                "Test screen",
                isMovePreviousPageAvailable = true,
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TestScreenContents(

            )
        }
    }

    LaunchedEffect(argument.event) {
        argument.event.collect { event ->
            when (event) {
                else -> {}
            }
        }
    }
}

@Composable
private fun TestScreenContents() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("This is Test Screen")

    }
}


@Preview(apiLevel = 34)
@Composable
private fun TestScreenPreview() {
    SiriaTemplateTheme {
        TestScreen(
            navController = rememberNavController(),
            argument = TestArgument(
                intent = { },
                state = TestState.Init,
                event = MutableSharedFlow()
            )
        )
    }
}