package com.example.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.ExampleModel
import com.example.domain.model.RandomUser
import com.example.presentation.common.nav.ScreenDestinations
import com.example.presentation.common.nav.safeNavigate
import com.example.presentation.common.ui.basic.BasicButton
import com.example.presentation.common.ui.basic.ButtonStyle
import com.example.presentation.common.ui.component.AppTopBar
import com.example.presentation.common.ui.custom.CustomTextFieldWithCondition
import com.example.presentation.common.ui.theme.SiriaTemplateTheme
import com.example.presentation.common.util.DefaultRoundedCorner
import com.example.presentation.common.util.Space16
import com.example.presentation.common.util.Space8
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun HomeScreen(
    navController: NavController,
    argument: HomeArgument,
    data: HomeData
) {
    val examples = data.examples
    val randomUser = data.randomUser

    var isCreateExampleSuccess by remember { mutableStateOf(false) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val onDeleteExampleIconClicked = { targetExample: ExampleModel ->
        argument.intent(HomeIntent.DeleteExample(targetExample))
    }

    val onCreateExampleIconClicked = { newExampleName: String ->
        argument.intent(
            HomeIntent.CreateNewExample(
                ExampleModel(id = 0L, name = newExampleName)
            )
        )
    }

    val navigateToTest = { navController.safeNavigate(ScreenDestinations.Home.Test.route) }

    Scaffold(
        topBar = { AppTopBar("Home screen", isMovePreviousPageAvailable = false) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            HomeScreenContents(
                examples = examples,
                randomUser = randomUser,
                onDeleteExampleIconClicked = onDeleteExampleIconClicked,
                onCreateExampleIconClicked = onCreateExampleIconClicked,
                navigateToTest = navigateToTest
            )
        }
    }

    if (isCreateExampleSuccess) {
        // Dialog to show the result of create example
    }

    LaunchedEffect(argument.event) {
        argument.event.collect { event ->
            when (event) {
                is HomeEvent.CreateExample.Success -> {
                    isCreateExampleSuccess = true
                    argument.intent(HomeIntent.Refresh)
                }

                is HomeEvent.DeleteExample.Success -> {
                    argument.intent(HomeIntent.Refresh)
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun HomeScreenContents(
    examples: List<ExampleModel>,
    randomUser: RandomUser,
    onDeleteExampleIconClicked: (ExampleModel) -> Unit,
    onCreateExampleIconClicked: (String) -> Unit,
    navigateToTest: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Test page",
            style = MaterialTheme.typography.headlineSmall
        )

        Space16()

        RandomUserCard(randomUser = randomUser)

        Space16()

        ExampleViewer(
            examples = examples,
            onDeleteExampleIconClicked = onDeleteExampleIconClicked
        )

        Space16()

        ExampleCreateForm(
            onCreateExampleIconClicked = onCreateExampleIconClicked
        )

        Space16()

        BasicButton(
            text = "다른 페이지로 이동",
            onClicked = { navigateToTest() },
            modifier = ButtonStyle.confirmButton()
        )
    }
}

@Composable
private fun RandomUserCard(randomUser: RandomUser) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = DefaultRoundedCorner
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "[userId / id] = ${randomUser.userId} / ${randomUser.id}")
        Text(text = "[title]\n${randomUser.title}")
        Text(text = "[body]\n${randomUser.body}")
    }
}

@Composable
private fun ExampleViewer(
    examples: List<ExampleModel>,
    onDeleteExampleIconClicked: (ExampleModel) -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = DefaultRoundedCorner
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "All Examples (Total : ${examples.count()})"
        )
        Space16()
        examples.forEach { example ->
            ExampleCard(example = example,
                onDeleteIconClicked = { onDeleteExampleIconClicked(example) })
            Space8()
        }
    }
}

@Composable
private fun ExampleCard(
    example: ExampleModel,
    onDeleteIconClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = Color.White, shape = DefaultRoundedCorner)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            Text(text = "id = ${example.id}")
            Text(text = "name = ${example.name}")
        }
        Icon(
            imageVector = Icons.Default.Clear,
            tint = Color.Black,
            contentDescription = "Delete",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onDeleteIconClicked()
                }
        )
    }
}

@Composable
private fun ExampleCreateForm(
    onCreateExampleIconClicked: (String) -> Unit
) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = DefaultRoundedCorner
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Create New Example",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomTextFieldWithCondition(
            value = inputText,
            onValueChange = { inputText = it },
            placeholder = "Enter new name...",
            modifier = Modifier.padding(bottom = 8.dp)
        )

        BasicButton(
            text = "+ 생성",
            onClicked = {
                onCreateExampleIconClicked(inputText)
                inputText = ""
            },
            modifier = ButtonStyle.confirmButton()
        )

        Space16()
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ExampleCardPreview() {
    ExampleCard(
        example = ExampleModel.empty().copy(id = 1, name = "first example"),
        onDeleteIconClicked = {},
    )
}

@Preview(apiLevel = 34)
@Composable
private fun HomeScreenPreview() {
    SiriaTemplateTheme {
        HomeScreen(
            navController = rememberNavController(),
            argument = HomeArgument(
                intent = { },
                state = HomeState.Init,
                event = MutableSharedFlow()
            ),
            data = HomeData(
                examples = listOf(
                    ExampleModel.empty().copy(id = 1, name = "first example"),
                    ExampleModel.empty().copy(id = 2, name = "second example"),
                ),
                randomUser = RandomUser.empty()
            )
        )
    }
}