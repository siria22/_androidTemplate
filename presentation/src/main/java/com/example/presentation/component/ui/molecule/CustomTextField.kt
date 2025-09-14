package com.example.presentation.component.ui.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.component.ui.atom.BasicInputTextField

@Composable
fun CustomTextFieldWithCondition(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    description: String = "",
    singleLine: Boolean = true,
    maxLength: Int = 20,
    allowNumberOnly: Boolean = false,
) {
    val regex = Regex("^\\d+$")
    fun validateInput(input: String): Boolean {
        if (allowNumberOnly && input.isBlank()) {
            onValueChange("0")
        }
        if (input.length > maxLength + 1) return false
        return true
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        BasicInputTextField(
            value = value,
            onValueChange = {
                if (((allowNumberOnly && it.matches(regex)) || !allowNumberOnly)
                    && validateInput(it)
                ) {
                    onValueChange(it)
                }
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = placeholder.ifEmpty { "${maxLength}자 이내로 입력해주세요" },
            singleLine = singleLine,
            keyboardOptions = if (allowNumberOnly) KeyboardOptions.Default
                .copy(keyboardType = KeyboardType.Number)
            else KeyboardOptions.Default
        )
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description.ifEmpty { "적절한 내용을 입력해주세요" },
                style = MaterialTheme.typography.bodyMedium,
                color = if (value.length <= maxLength) Color.Black
                else Color.Red,
                textAlign = TextAlign.Center
            )
            Text(
                text = "(${value.length} / ${maxLength})",
                style = MaterialTheme.typography.bodyMedium,
                color = if (value.length <= maxLength) Color.Black
                else Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(apiLevel = 34)
@Composable
private fun CustomTextFieldPreview() {

    var input by remember { mutableStateOf("") }
    val onValueChange: (String) -> Unit = { str -> input = str }

    CustomTextFieldWithCondition(
        value = input,
        onValueChange = onValueChange,
        maxLength = 20,
        allowNumberOnly = false
    )
}