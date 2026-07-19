package com.example.shared.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.shared.theme.MeshColor1
import com.example.shared.theme.MeshColor2
import com.example.shared.theme.MeshColor3
import com.example.shared.theme.Midnight

@Composable
fun MeshBackground(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "mesh")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(90000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        drawRect(color = Midnight)
        
        val width = size.width
        val height = size.height

        val x1 = width * (0.5f + 0.3f * kotlin.math.cos(phase))
        val y1 = height * (0.3f + 0.2f * kotlin.math.sin(phase * 0.7f))

        val x2 = width * (0.2f + 0.4f * kotlin.math.sin(phase * 1.3f))
        val y2 = height * (0.7f + 0.3f * kotlin.math.cos(phase * 0.9f))

        val x3 = width * (0.8f + 0.2f * kotlin.math.cos(phase * 1.1f))
        val y3 = height * (0.8f + 0.2f * kotlin.math.sin(phase * 0.8f))

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(MeshColor1.copy(alpha = 0.6f), Color.Transparent),
                center = Offset(x1, y1),
                radius = width * 0.8f
            ),
            center = Offset(x1, y1),
            radius = width * 0.8f
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(MeshColor2.copy(alpha = 0.5f), Color.Transparent),
                center = Offset(x2, y2),
                radius = width * 0.9f
            ),
            center = Offset(x2, y2),
            radius = width * 0.9f
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(MeshColor3.copy(alpha = 0.5f), Color.Transparent),
                center = Offset(x3, y3),
                radius = width * 0.7f
            ),
            center = Offset(x3, y3),
            radius = width * 0.7f
        )
    }
}
