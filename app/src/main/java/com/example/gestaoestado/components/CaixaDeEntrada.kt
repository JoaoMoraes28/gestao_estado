package com.example.gestaoestado.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun GetTextField(
    modifier: Modifier,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    value: String,
    atualizarValor: (String) -> Unit
) {
    OutlinedTextField(
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = modifier,
        value = value,
        onValueChange = {
            atualizarValor(it)
        }

    )

}