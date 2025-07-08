package com.example.data.remote

import com.example.data.remote.local.entity.ExampleEntity
import com.example.data.remote.network.model.RandomUserResponse
import com.example.domain.model.ExampleModel
import com.example.domain.model.RandomUser

fun ExampleModel.toEntity(): ExampleEntity {
    return ExampleEntity(id = id, name = name)
}

fun ExampleEntity.toDomain(): ExampleModel {
    return ExampleModel(id = id, name = name)
}

fun RandomUserResponse.toDomain(): RandomUser {
    return RandomUser(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

fun RandomUser.toEntity(): RandomUserResponse {
    return RandomUserResponse(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}