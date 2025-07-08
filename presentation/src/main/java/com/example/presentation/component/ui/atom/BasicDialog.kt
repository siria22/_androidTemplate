package com.example.presentation.component.ui.atom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.component.theme.LargeRoundedCorner
import com.example.presentation.component.theme.SiriaTemplateColorScheme

@Composable
fun BasicDialog(
    minimumWidth: Float = 0.8f,
    backHandler: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
            .clickable(
                enabled = true,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) { }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = SiriaTemplateColorScheme.background,
                    shape = LargeRoundedCorner
                )
                .padding(24.dp)
                .fillMaxWidth(minimumWidth),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            content()
        }
        BackHandler { backHandler() }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun BasicDialogPreview() {
    BasicDialog {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sample Text",
                style = MaterialTheme.typography.bodyMedium,
                color = SiriaTemplateColorScheme.descriptionText
            )
            Text(
                text = "Sample Text",
                style = MaterialTheme.typography.headlineSmall,
                color = SiriaTemplateColorScheme.commonText
            )
        }
    }
}