package com.example.domain.usecase.example

import com.example.domain.model.ExampleModel
import com.example.domain.repository.ExampleRepository
import javax.inject.Inject

class GetAllExamplesUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(): Result<List<ExampleModel>> {
        return exampleRepository.getAllExampleEntity()
    }
}
