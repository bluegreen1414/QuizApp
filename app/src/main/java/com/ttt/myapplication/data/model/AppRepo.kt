package com.ttt.myapplication.data.model

import com.ttt.myapplication.data.constants.ConsBoolean
import com.ttt.myapplication.data.constants.ConsMulti
import com.ttt.myapplication.data.constants.ConsSingle

object AppRepo {
    fun getSingle(): List<String> = singleAnswer
    fun getMulti(): List<String> = multiAnswers
    fun getBoolean(): List<String> = boolean
}

private val singleAnswer = listOf(
    ConsSingle.JAVA,
    ConsSingle.KOTLIN,
    ConsSingle.REACT
)

private val multiAnswers = listOf(
    ConsMulti.COIL,
    ConsMulti.OKHTTP,
    ConsMulti.RETROFIT,
    ConsMulti.FIREBASE,
    ConsMulti.GLIDE
)

private val boolean = listOf(
    ConsBoolean.TRUE,
    ConsBoolean.FALSE
)
