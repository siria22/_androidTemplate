package com.example.presentation.screen.home

import com.example.domain.model.ExampleModel
import com.example.domain.model.RandomUser

data class HomeData(
    val examples: List<ExampleModel>,
    val randomUser: RandomUser
) {
    fun count(): Int = examples.size
}