package com.example.domain.model

data class RandomUser(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
) {
    companion object {
        fun empty() = RandomUser(
            userId = 0,
            id = 0,
            title = "RandomTitle",
            body = "bodybody"
        )
    }
}