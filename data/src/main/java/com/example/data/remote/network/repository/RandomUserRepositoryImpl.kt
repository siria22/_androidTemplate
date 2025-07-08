package com.example.data.remote.network.repository

import com.example.data.remote.network.RandomUserApi
import com.example.data.remote.toDomain
import com.example.domain.exception.RandomUserFetchFailureException
import com.example.domain.model.RandomUser
import com.example.domain.repository.RandomUserRepository
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserApi: RandomUserApi
) : RandomUserRepository {

    override suspend fun fetchRandomUser(): Result<RandomUser> = runCatching {
        val response = randomUserApi.fetchRandomUser().getOrElse {
            throw RandomUserFetchFailureException(cause = it)
        }
        response.toDomain()
    }
}
