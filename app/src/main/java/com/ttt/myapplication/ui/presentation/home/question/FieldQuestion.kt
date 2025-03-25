package com.ttt.myapplication.ui.presentation.home.question

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ttt.myapplication.common.composable.DescriptionField
import com.ttt.myapplication.common.ext.fieldModifier
import com.ttt.myapplication.ui.presentation.home.QuestionWrapper

@Composable
fun FieldQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    @StringRes text: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    QuestionWrapper(
        modifier = modifier,
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
    ) {
        val fieldModifier = Modifier.fieldModifier()
        DescriptionField(text, value, onValueChange, fieldModifier)
    }
}
