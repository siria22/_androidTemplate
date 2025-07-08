package com.example.presentation.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.common.nav.safePopSBackStack
import com.example.presentation.common.ui.theme.CustomColor
import com.example.presentation.common.ui.theme.SiriaTemplateTheme
import com.example.presentation.common.util.dropShadow

@Composable
fun AppTopBar(
    text: String,
    background: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
    isMovePreviousPageAvailable: Boolean = false,
    navController: NavController? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                color = CustomColor.darkPrimary,
                offsetY = 2.dp,
            )
            .height(56.dp)
            .background(
                color = background,
            )
    ) {
        if (isMovePreviousPageAvailable) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                tint = Color.White,
                contentDescription = "move back",
                modifier = Modifier
                    .clickable {
                        navController!!.safePopSBackStack()
                    }
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .size(36.dp)
            )
        }
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(apiLevel = 34)
@Composable
private fun AppTopBarPreview2() {
    SiriaTemplateTheme {
        val textList = listOf(
            "First example",
            "Second Example",
        )
        Column {
            textList.forEach { text ->
                AppTopBar(text = text, modifier = Modifier.padding(bottom = 10.dp))
            }
            AppTopBar(
                text = "뒤로가기도 있음",
                isMovePreviousPageAvailable = true
            )
        }
    }
}