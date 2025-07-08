package com.example.presentation.common.ui.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.common.ui.theme.CustomColor
import com.example.presentation.common.ui.theme.SiriaTemplateTheme
import com.example.presentation.common.util.RoundedCornerLarge
import com.example.presentation.common.util.dropShadow

@Composable
fun BasicButton(
    text: String,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onClicked() }
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

object ButtonStyle {
    @Composable
    fun confirmButton(): Modifier {
        return Modifier
            .dropShadow(
                RoundedCornerLarge,
                CustomColor.darkPrimary,
                offsetY = 4.dp
            )
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerLarge
            )
    }

    @Composable
    fun cancelButton(): Modifier {
        return Modifier
            .dropShadow(
                RoundedCornerLarge,
                CustomColor.darkPrimary,
                offsetY = 4.dp
            )
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerLarge
            )
    }
}

@Preview
@Composable
private fun AppButtonPreview() {
    SiriaTemplateTheme {
        BasicButton(
            text = "Button",
            onClicked = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )
    }
}