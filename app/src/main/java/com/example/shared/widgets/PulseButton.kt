package com.example.shared.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shared.theme.SignalGradient
import com.example.shared.theme.SpringBouncy

@Composable
fun PulseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean = false
) {
    val scale by animateFloatAsState(
        targetValue = if (isActive) 1.1f else 1f,
        animationSpec = SpringBouncy,
        label = "pulseScale"
    )

    Box(
        modifier = modifier
            .size(64.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(SignalGradient)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        // Subtle outer glow
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(16.dp)
                .background(SignalGradient)
        )
    }
}
