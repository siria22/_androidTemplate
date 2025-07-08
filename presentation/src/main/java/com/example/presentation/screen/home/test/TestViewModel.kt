package com.example.presentation.screen.home.test

import com.example.presentation.common.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow<TestState>(TestState.Init)
    val state: StateFlow<TestState> = _state

    private val _eventFlow = MutableSharedFlow<TestEvent>()
    val eventFlow: SharedFlow<TestEvent> = _eventFlow

    fun onIntent(intent: TestIntent) {
        when (intent) {
            else -> {}
        }
    }
}