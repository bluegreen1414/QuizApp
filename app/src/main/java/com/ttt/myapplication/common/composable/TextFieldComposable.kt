package com.ttt.myapplication.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionField(
    @StringRes text: Int,
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = { onNewValue(it)},
        placeholder = { Text(stringResource(text)) },
        modifier = modifier
            .height(350.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Blue,
            unfocusedLabelColor = Color.Blue,//Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary
        )
    )
}
