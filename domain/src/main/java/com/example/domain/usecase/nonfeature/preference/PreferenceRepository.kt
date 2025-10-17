package com.example.domain.usecase.nonfeature.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {

    fun getTheme(): Flow<Result<String>>

    suspend fun updateTheme(theme: String): Result<Unit>
}