package com.example.domain.usecase.example

import com.example.domain.model.ExampleModel
import com.example.domain.repository.ExampleRepository
import javax.inject.Inject

class DeleteExampleUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(targetExampleModel: ExampleModel): Result<Unit> {
        return exampleRepository.deleteExampleEntity(targetExampleModel)
    }
}
