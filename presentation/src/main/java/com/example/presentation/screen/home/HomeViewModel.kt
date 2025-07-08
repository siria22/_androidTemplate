package com.example.presentation.screen.home

import com.example.domain.model.ExampleModel
import com.example.domain.model.RandomUser
import com.example.domain.usecase.example.CreateExampleUseCase
import com.example.domain.usecase.example.DeleteExampleUseCase
import com.example.domain.usecase.example.GetAllExamplesUseCase
import com.example.domain.usecase.network.FetchRandomUserUseCase
import com.example.presentation.common.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createExampleUseCase: CreateExampleUseCase,
    private val deleteExampleUseCase: DeleteExampleUseCase,
    private val getAllExamplesUseCase: GetAllExamplesUseCase,
    private val fetchRandomUserUseCase: FetchRandomUserUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Init)
    val state: StateFlow<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow: SharedFlow<HomeEvent> = _eventFlow

    private val _examples = MutableStateFlow<List<ExampleModel>>(emptyList())
    val examples: StateFlow<List<ExampleModel>> = _examples

    private val _randomUser = MutableStateFlow(RandomUser.empty())
    val randomUser: StateFlow<RandomUser> = _randomUser

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.GetAllExamples -> {
                launch { getAllExamples() }
            }

            is HomeIntent.DeleteExample -> {
                launch { deleteExample(intent.targetExample) }
            }

            is HomeIntent.CreateNewExample -> {
                launch { createExample(intent.newExample) }
            }

            is HomeIntent.Refresh -> {
                launch {
                    fetchServerTime()
                    getAllExamples()
                }
            }
        }
    }

    init {
        launch {
            fetchServerTime()
            getAllExamples()
        }
    }

    private suspend fun getAllExamples() {
        _state.value = HomeState.OnProgress

        runCatching {
            getAllExamplesUseCase()
        }.onSuccess { result ->
            _examples.value = result.getOrThrow()
            _eventFlow.emit(HomeEvent.FetchExample.Success)
        }.onFailure { exception ->
            _examples.value = emptyList()
            _eventFlow.emit(HomeEvent.FetchExample.Error("Error: msg\n${exception.message}"))
            _state.value = HomeState.Init
        }
        _state.value = HomeState.Init
    }

    private suspend fun createExample(newExample: ExampleModel) {
        _state.value = HomeState.OnProgress

        runCatching {
            createExampleUseCase(newExample)
        }.onSuccess {
            _eventFlow.emit(HomeEvent.CreateExample.Success)
        }.onFailure {
            _eventFlow.emit(HomeEvent.CreateExample.Error("Error: msg\n${it.message}"))
            _state.value = HomeState.Init
        }
        _state.value = HomeState.Init
    }

    private suspend fun deleteExample(targetExample: ExampleModel) {
        _state.value = HomeState.OnProgress

        runCatching {
            deleteExampleUseCase(targetExample)
        }.onSuccess {
            _eventFlow.emit(HomeEvent.DeleteExample.Success)
        }.onFailure {
            _eventFlow.emit(HomeEvent.DeleteExample.Error("Error: msg\n${it.message}"))
            _state.value = HomeState.Init
        }
        _state.value = HomeState.Init
    }

    private suspend fun fetchServerTime() {
        runCatching {
            fetchRandomUserUseCase()
        }.onSuccess { result ->
            _randomUser.value = result.getOrThrow()
        }.onFailure {
            //TODO : Exception handling
            _state.value = HomeState.Init
        }
        _state.value = HomeState.Init
    }
}