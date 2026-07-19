package com.example.shared.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Midnight = Color(0xFF05060A)
val Ink = Color(0xFFF5F6FA)
val Fog = Color(0xFFA6ABBD)

val GlassSurface = Color.White.copy(alpha = 0.06f)
val GlassSurfaceElevated = Color.White.copy(alpha = 0.10f)
val Hairline = Color.White.copy(alpha = 0.12f)

val DiffAdd = Color(0xFF32D583)
val DiffRemove = Color(0xFFFF6166)
val DiffWarn = Color(0xFFFFC069)

val SignalOrange = Color(0xFFFF6A39)
val SignalPink = Color(0xFFFF3D7F)

val SignalGradient = Brush.linearGradient(
    colors = listOf(SignalOrange, SignalPink)
)

val MeshColor1 = Color(0xFF1C1F3B)
val MeshColor2 = Color(0xFF2E1A47)
val MeshColor3 = Color(0xFF0F2438)
