package com.ttt.myapplication.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.material.icons.outlined.DoneOutline

@Composable
private fun arrowIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon

@Composable
fun arrowBackIcon() = arrowIcon(ltrIcon = Icons.AutoMirrored.Outlined.ArrowBack,
    rtlIcon = Icons.AutoMirrored.Outlined.ArrowForward)

object AppIcons {
    val Done = Icons.Outlined.DoneOutline
    val Close = Icons.Filled.Close
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
