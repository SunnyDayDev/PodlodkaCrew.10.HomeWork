package com.example.crewhomework.design

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FlippableCard(
    modifier: Modifier = Modifier,
    colors: CardColors,
    isFlipped: Boolean,
    backContent: @Composable () -> Unit = {},
    faceContent: @Composable () -> Unit,
) {
    val flipAnimationAngle by animateFloatAsState(
        targetValue = if (isFlipped) 0f else 180f,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ),
        label = "flip"
    )

    Card(
        colors = colors,
        modifier = modifier
            .graphicsLayer {
                rotationX = flipAnimationAngle
                cameraDistance = 16f
            }
    ) {
        if (flipAnimationAngle > 90) {
            backContent()
        } else {
            faceContent()
        }
    }
}