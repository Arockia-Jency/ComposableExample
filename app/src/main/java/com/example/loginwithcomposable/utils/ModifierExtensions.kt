package com.example.loginwithcomposable.utils


import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.shake(enabled: Boolean): Modifier = composed {
    if (!enabled) return@composed this

    val offset = remember { Animatable(0f) }

    LaunchedEffect(enabled) {
        offset.snapTo(0f)
        offset.animateTo(
            targetValue = 10f,
            animationSpec = repeatable(
                iterations = 4,
                animation = tween(50),
                repeatMode = RepeatMode.Reverse
            )
        )
        offset.animateTo(0f)
    }

    this.graphicsLayer {
        translationX = offset.value
    }
}