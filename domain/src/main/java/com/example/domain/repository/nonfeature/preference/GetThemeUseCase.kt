package com.example.domain.repository.nonfeature.preference

import com.example.domain.model.type.AppTheme
import com.example.domain.model.type.toAppTheme
import com.example.domain.usecase.nonfeature.preference.PreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    operator fun invoke(): Flow<Result<AppTheme>> {
        return preferenceRepository.getTheme().map { result ->
            result.map { it.toAppTheme() }
        }
    }
}
