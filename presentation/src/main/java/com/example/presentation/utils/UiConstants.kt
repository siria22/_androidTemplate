package com.example.presentation.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Spacer
@Composable
fun Space8() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Space16() {
    Spacer(modifier = Modifier.height(16.dp))
}


// RoundedCornerShape

val DefaultRoundedCorner = RoundedCornerShape(8.dp)
val RoundedCornerLarge = RoundedCornerShape(16.dp)