package com.ttt.myapplication.ui.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import com.ttt.myapplication.common.composable.AppIcons
import com.ttt.myapplication.R.string as AppText

private const val CONTENT_ANIMATION_DURATION = 300

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = hiltViewModel()
) {
    val surveyScreenData = viewModel.surveyScreenData ?: return
    val uiState by viewModel.uiState

    BackHandler {
        if (!viewModel.onBackPressed()) {
            viewModel.onCloseChange(true)
        }
    }

    HomeScreen(
        surveyScreenData = surveyScreenData,
        uiState = uiState,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = { viewModel.onCloseChange(true)},
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed() },
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)

        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(CONTENT_ANIMATION_DURATION)

                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetIndex = targetState.questionIndex,
                )

                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec,
                ) togetherWith slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            },
            label = "surveyScreenDataAnimation"
        ) { targetState ->

            when (targetState.surveyQuestion) {
                SurveyQuestion.SINGLE_QUESTION -> {
                    SingleQuestion(
                        selectedAnswer = viewModel.singleQuestion,
                        onOptionSelected = viewModel::onSingleQuestionChange,
                        modifier = modifier,
                    )
                }
                SurveyQuestion.MULTI_QUESTION -> MultiQuestion(
                    selectedAnswers = viewModel.multiQuestionResponse,
                    onOptionSelected = viewModel::onMultiChooseResponse,
                    modifier = modifier
                )
                SurveyQuestion.FIELD_QUESTION -> {
                    FieldQuestion(
                        value = viewModel.fieldQuestion ?: "",
                        onValueChange = viewModel::onFieldQuestionChange,
                        modifier = modifier,
                    )
                }
                SurveyQuestion.BOOLEAN_QUESTION -> BooleanQuestion(
                    selectedAnswer = viewModel.booleanQuestion,
                    onOptionSelected = viewModel::onBooleanQuestionChange,
                    modifier = modifier,
                )
            }
        }
    }
}

private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}
