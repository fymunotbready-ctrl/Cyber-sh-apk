package com.example.features.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.GlassSurfaceElevated
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.widgets.GlassCard
import com.example.shared.widgets.MeshBackground
import com.example.shared.widgets.Mode
import com.example.shared.widgets.PulseButton
import com.example.shared.widgets.RailEvent
import com.example.shared.widgets.SessionRail
import java.time.Instant

@Composable
fun SessionScreen(
    mode: Mode,
    events: List<RailEvent>,
    onSend: (String) -> Unit
) {
    Scaffold(
        containerColor = Midnight
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            MeshBackground()
            
            Column(modifier = Modifier.fillMaxSize()) {
                // Top App Bar
                GlassCard(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    surfaceColor = GlassSurfaceElevated
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = mode.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Ink
                        )
                    }
                }
                
                // Rail
                SessionRail(
                    events = events,
                    modifier = Modifier.weight(1f).padding(top = 16.dp)
                )
                
                // Composer
                Composer(
                    modifier = Modifier.padding(16.dp),
                    onSend = onSend
                )
            }
        }
    }
}

@Composable
private fun Composer(
    modifier: Modifier = Modifier,
    onSend: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlassCard(
            modifier = Modifier.weight(1f),
            shape = CircleShape
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Ink),
                cursorBrush = SolidColor(Ink),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                        if (text.isEmpty()) {
                            Text(
                                text = "Message cybersh...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Fog
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        PulseButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSend(text)
                    text = ""
                }
            },
            isActive = text.isNotBlank()
        )
    }
}
