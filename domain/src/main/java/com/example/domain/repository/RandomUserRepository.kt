package com.example.domain.repository

import com.example.domain.model.RandomUser

interface RandomUserRepository {
    suspend fun fetchRandomUser(): Result<RandomUser>
}