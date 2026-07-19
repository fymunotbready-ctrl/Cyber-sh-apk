package com.example.shared.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

val SpringBouncy = spring<Float>(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow
)

fun <T> springLayout() = spring<T>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessMedium
)
