package com.ttt.myapplication.ui.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ttt.myapplication.R.string as AppText
import com.ttt.myapplication.data.model.AppRepo
import com.ttt.myapplication.ui.presentation.home.question.SingleChoiceQuestion
import com.ttt.myapplication.ui.presentation.home.question.FieldQuestion
import com.ttt.myapplication.ui.presentation.home.question.MultipleChoiceQuestion

@Composable
fun SingleQuestion(
    selectedAnswer: String?,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = AppText.single_choose,
        directionsResourceId = AppText.select_one,
        possibleAnswers = AppRepo.getSingle(),
        selectedAnswer = selectedAnswer,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}

@Composable
fun MultiQuestion(
    selectedAnswers: List<String>,
    onOptionSelected: (selected: Boolean, answer: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MultipleChoiceQuestion(
        titleResourceId = AppText.multi_choose,
        directionsResourceId = AppText.select_all,
        possibleAnswers = AppRepo.getMulti(),
        selectedAnswers = selectedAnswers,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}


@Composable
fun FieldQuestion(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    FieldQuestion(
        titleResourceId = AppText.field_question,
        directionsResourceId = AppText.write_answer,
        text = AppText.text,
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
    )
}


@Composable
fun BooleanQuestion(
    selectedAnswer: String?,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = AppText.boolean_question,
        directionsResourceId = AppText.select_one,
        possibleAnswers = AppRepo.getBoolean(),
        selectedAnswer = selectedAnswer,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}
