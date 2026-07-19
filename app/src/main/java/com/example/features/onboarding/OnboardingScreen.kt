package com.example.features.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.theme.PillShape
import com.example.shared.theme.SignalGradient
import com.example.shared.widgets.DiffEvent
import com.example.shared.widgets.MeshBackground
import com.example.shared.widgets.Mode
import com.example.shared.widgets.RailEvent
import com.example.shared.widgets.SessionRail
import com.example.shared.widgets.TextEvent
import kotlinx.coroutines.delay
import java.time.Instant

@Composable
fun OnboardingScreen(
    onTryIt: () -> Unit,
    onSignIn: () -> Unit
) {
    var stage by remember { mutableStateOf(0) }
    
    val events = remember { mutableListOf<RailEvent>() }

    LaunchedEffect(Unit) {
        delay(800) // Let it breathe
        stage = 1
        
        delay(500)
        events.add(
            TextEvent(
                id = "1",
                timestamp = Instant.now(),
                mode = Mode.AGENT,
                text = "Implement frosted glass cards across the app.",
                fromUser = true
            )
        )
        stage = 2
        
        delay(1500)
        events.add(
            TextEvent(
                id = "2",
                timestamp = Instant.now(),
                mode = Mode.AGENT,
                text = "I've added the GlassCard widget and updated the theme colors. Here is the diff.",
                fromUser = false
            )
        )
        stage = 3
        
        delay(1000)
        events.add(
            DiffEvent(
                id = "3",
                timestamp = Instant.now(),
                mode = Mode.AGENT,
                filename = "GlassCard.kt",
                addedLines = listOf("+ class GlassCard"),
                removedLines = listOf()
            )
        )
        stage = 4
        
        delay(2000)
        stage = 5 // Show headline and buttons
    }

    Scaffold(
        containerColor = Midnight
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            MeshBackground()
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                // Rail Fragment
                AnimatedVisibility(
                    visible = stage >= 2,
                    enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(initialOffsetY = { 100 }, animationSpec = tween(1000)),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        SessionRail(events = events)
                        
                        // Dimmer overlay when headline appears
                        AnimatedVisibility(
                            visible = stage >= 5,
                            enter = fadeIn(animationSpec = tween(2000))
                        ) {
                            Box(modifier = Modifier.matchParentSize().background(Midnight.copy(alpha = 0.6f)))
                        }
                    }
                }
                
                // Headline & Buttons
                AnimatedVisibility(
                    visible = stage >= 5,
                    enter = fadeIn(animationSpec = tween(1000, delayMillis = 500)),
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 64.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "It ships while you're\nthinking about lunch.",
                            style = MaterialTheme.typography.displayLarge,
                            color = Ink,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(48.dp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clip(PillShape)
                                .background(SignalGradient)
                                .clickable(onClick = onTryIt),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Try it",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold),
                                color = Ink
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Text(
                            text = "I already have an account",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Fog,
                            modifier = Modifier.clickable(onClick = onSignIn)
                        )
                    }
                }
            }
        }
    }
}
