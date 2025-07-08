package com.example.presentation.screen.home.test

import kotlinx.coroutines.flow.SharedFlow

data class TestArgument(
    val intent: (TestIntent) -> Unit,
    val state: TestState,
    val event: SharedFlow<TestEvent>
)

sealed class TestState {
    data object Init : TestState()
    data object OnProgress : TestState()
}

sealed class TestIntent

sealed class TestEvent {
    sealed class SomeEvent : TestEvent() {
        data object Success : SomeEvent()
        data class Error(val message: String) : SomeEvent()
    }
}