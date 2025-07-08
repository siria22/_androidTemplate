package com.example.data.remote.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomUserResponse(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
)
