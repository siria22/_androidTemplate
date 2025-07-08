package com.example.domain.usecase.network

import android.util.Log
import com.example.domain.exception.RandomUserFetchFailureException
import com.example.domain.model.RandomUser
import com.example.domain.repository.RandomUserRepository
import javax.inject.Inject

class FetchRandomUserUseCase @Inject constructor(
    private val randomUserRepository: RandomUserRepository
) {
    suspend operator fun invoke(): Result<RandomUser> {
        return randomUserRepository.fetchRandomUser().mapCatching { user ->
            user
        }.onFailure { e ->
            if (e !is RandomUserFetchFailureException) {
                Log.d("siria22", "Failed to fetch user")
                throw RandomUserFetchFailureException(cause = e)
            }
        }
    }
}
