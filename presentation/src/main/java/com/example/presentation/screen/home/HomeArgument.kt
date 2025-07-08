package com.example.presentation.screen.home

import com.example.domain.model.ExampleModel
import kotlinx.coroutines.flow.SharedFlow

data class HomeArgument(
    val intent: (HomeIntent) -> Unit,
    val state: HomeState,
    val event: SharedFlow<HomeEvent>
)

sealed class HomeState {
    data object Init : HomeState()
    data object OnProgress : HomeState()
}

sealed class HomeIntent {
    data class CreateNewExample(val newExample: ExampleModel) : HomeIntent()
    data class DeleteExample(val targetExample: ExampleModel) : HomeIntent()
    data object GetAllExamples : HomeIntent()
    data object Refresh: HomeIntent()
}

sealed class HomeEvent {
    sealed class FetchExample : HomeEvent() {
        data object Success : FetchExample()
        data class Error(val message: String) : FetchExample()
    }

    sealed class CreateExample : HomeEvent() {
        data object Success : CreateExample()
        data class Error(val message: String) : CreateExample()
    }

    sealed class DeleteExample : HomeEvent() {
        data object Success : DeleteExample()
        data class Error(val message: String) : DeleteExample()
    }
}