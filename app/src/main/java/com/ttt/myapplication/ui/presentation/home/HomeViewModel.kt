package com.ttt.myapplication.ui.presentation.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.ttt.myapplication.ui.presentation.main.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
): MainViewModel() {
    var uiState = mutableStateOf(HomeUiState())
        private set

    private val questionOrder: List<SurveyQuestion> =
        listOf(
            SurveyQuestion.SINGLE_QUESTION,
            SurveyQuestion.MULTI_QUESTION,
            SurveyQuestion.FIELD_QUESTION,
            SurveyQuestion.BOOLEAN_QUESTION,
        )

    private var questionIndex = 0



    private val _singleQuestion = mutableStateOf<String?>(null)
    val singleQuestion: String?
        get() = _singleQuestion.value

    private val _multiQuestionResponse = mutableStateListOf<String>()
    val multiQuestionResponse: List<String>
        get() = _multiQuestionResponse

    private val _fieldQuestion = mutableStateOf<String?>(null)
    val fieldQuestion: String?
        get() = _fieldQuestion.value

    private val _booleanQuestion = mutableStateOf<String?>(null)
    val booleanQuestion: String?
        get() = _booleanQuestion.value


    private val _surveyScreenData = mutableStateOf(createSurveyScreenData())
    val surveyScreenData: SurveyScreenData?
        get() = _surveyScreenData.value

    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: Boolean
        get() = _isNextEnabled.value


    fun onBackPressed(): Boolean {
        if (questionIndex == 0) {
            return false
        }
        changeQuestion(questionIndex - 1)
        return true
    }

    fun onPreviousPressed() {
        if (questionIndex == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeQuestion(questionIndex - 1)
    }

    fun onNextPressed() {
        changeQuestion(questionIndex + 1)
    }

    private fun changeQuestion(newQuestionIndex: Int) {
        questionIndex = newQuestionIndex
        _isNextEnabled.value = getIsNextEnabled()
        _surveyScreenData.value = createSurveyScreenData()
    }


    fun onDonePressed() {
        launchCatching {
            onDoneChange(true)
        }
    }

    fun onSingleQuestionChange(newValue: String) {
        _singleQuestion.value = newValue
        _isNextEnabled.value = getIsNextEnabled()
    }
    fun onMultiChooseResponse(selected: Boolean, answer: String) {
        if (selected) {
            _multiQuestionResponse.add(answer)
        } else {
            _multiQuestionResponse.remove(answer)
        }
        _isNextEnabled.value = getIsNextEnabled()
    }
    fun onFieldQuestionChange(newValue: String) {
        _fieldQuestion.value = newValue
        _isNextEnabled.value = getIsNextEnabled()
    }
    fun onBooleanQuestionChange(newValue: String) {
        _booleanQuestion.value = newValue
        _isNextEnabled.value = getIsNextEnabled()
    }




    private fun getIsNextEnabled(): Boolean {
        return when (questionOrder[questionIndex]) {
            SurveyQuestion.SINGLE_QUESTION -> _singleQuestion.value != null
            SurveyQuestion.MULTI_QUESTION -> _multiQuestionResponse.isNotEmpty()
            SurveyQuestion.FIELD_QUESTION -> _fieldQuestion.value != null
            SurveyQuestion.BOOLEAN_QUESTION -> _booleanQuestion.value != null
        }
    }
    private fun createSurveyScreenData(): SurveyScreenData {
        return SurveyScreenData(
            questionIndex = questionIndex,
            questionCount = questionOrder.size,
            shouldShowPreviousButton = questionIndex > 0,
            shouldShowDoneButton = questionIndex == questionOrder.size - 1,
            surveyQuestion = questionOrder[questionIndex],
        )
    }

    private fun onDoneChange(newValue: Boolean) { uiState.value = uiState.value.copy(showDone = newValue) }
    fun onCloseChange(newValue: Boolean) { uiState.value = uiState.value.copy(showClose = newValue) }
}


enum class SurveyQuestion {
    SINGLE_QUESTION,
    MULTI_QUESTION,
    FIELD_QUESTION,
    BOOLEAN_QUESTION
}

data class SurveyScreenData(
    val questionIndex: Int,
    val questionCount: Int,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val surveyQuestion: SurveyQuestion,
)
