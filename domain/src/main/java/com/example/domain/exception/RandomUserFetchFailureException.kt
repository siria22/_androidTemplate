package com.example.domain.exception

class RandomUserFetchFailureException(
    override val message: String = "Failed to fetch random user",
    override val cause: Throwable? = null
) : Exception(message, cause)
