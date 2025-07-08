package com.example.data.remote.local.repository

import com.example.data.remote.local.dao.ExampleDao
import com.example.data.remote.toDomain
import com.example.data.remote.toEntity
import com.example.domain.model.ExampleModel
import com.example.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDao: ExampleDao
) : ExampleRepository {

    override suspend fun createExampleEntity(exampleModel: ExampleModel): Result<Unit> =
        runCatching {
            exampleDao.createExampleEntity(exampleModel.toEntity())
            return Result.success(Unit)
        }.onFailure {
            return Result.failure(it)
        }

    override suspend fun getAllExampleEntity(): Result<List<ExampleModel>> = runCatching {
        exampleDao.getAllExampleEntity().map { it.toDomain() }
    }.onFailure {
        return Result.failure(it)
    }

    override suspend fun deleteExampleEntity(exampleModel: ExampleModel): Result<Unit> =
        runCatching {
            exampleDao.deleteExampleEntity(exampleModel.toEntity())
        }.onFailure {
            return Result.failure(it)
        }

}