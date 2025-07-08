package com.example.presentation.common.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Temp Primary
val LightBlue100 = Color(0xffb6e6ff)
val LightBlue200 = Color(0xff86d6ff)
val LightBlue300 = Color(0xff58c6ff) // Primary - Light
val LightBlue400 = Color(0xFF3bb9ff)
val LightBlue500 = Color(0xff2eacfd)
val LightBlue600 = Color(0xff2d9eee)
val LightBlue700 = Color(0xff2a8bd9)
val LightBlue800 = Color(0xff277ac5)
val LightBlue900 = Color(0xff2359a2)

// Temp Secondary
val NeonGreen100 = Color(0xffb6f2c7)
val NeonGreen200 = Color(0xff80eaa2) // Secondary - Light
val NeonGreen300 = Color(0xff3fe07f)
val NeonGreen400 = Color(0xff00d567)
val NeonGreen500 = Color(0xff00cb4e)
val NeonGreen600 = Color(0xff00bb44)
val NeonGreen700 = Color(0xff00a837)
val NeonGreen800 = Color(0xff00962c)
val NeonGreen900 = Color(0xff007517)

val Gray100 = Color(0xFFFAFAFA)
val Gray200 = Color(0xFFF5F5F5)
val Gray300 = Color(0xFFE0E0E0) // LightGray - Light
val Gray400 = Color(0xFFBDBDBD)
val Gray500 = Color(0xFF9E9E9E)
val Gray600 = Color(0xFF757575)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF424242) // DarkGray
val Gray900 = Color(0xFF212121)

object CustomColor {
    val darkPrimary = LightBlue500
}

@Composable
private fun ColorPreviewBox2(colorName: String, color: Color, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = colorName, color = color)
    }
}

@Preview(apiLevel = 34)
@Composable
private fun ColorPreview() {
    SiriaTemplateTheme {
        Column {
            ColorPreviewBox2("L_Primary", colorScheme.onPrimary, colorScheme.primary)
            ColorPreviewBox2("L_Secondary", colorScheme.onSecondary, colorScheme.secondary)
            ColorPreviewBox2("L_Background", colorScheme.onBackground, colorScheme.background)
            ColorPreviewBox2("L_Surface", colorScheme.onSurface, colorScheme.surface)
        }
    }
}