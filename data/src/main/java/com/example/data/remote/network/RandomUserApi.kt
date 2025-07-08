package com.example.data.remote.network

import android.util.Log
import com.example.data.remote.network.model.RandomUserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RandomUserApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun fetchRandomUser(): Result<RandomUserResponse> {
        return runCatching {
            val response = client.get("https://jsonplaceholder.typicode.com/posts/1")
            response.body<RandomUserResponse>()
        }.onFailure { e ->
            Log.e("siria22", "Failed to fetch Random User", e)
        }
    }
}
