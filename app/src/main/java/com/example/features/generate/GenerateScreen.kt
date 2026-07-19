package com.example.features.generate

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.Hairline
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.theme.PillShape
import com.example.shared.theme.SignalGradient
import com.example.shared.widgets.GlassCard
import com.example.shared.widgets.MeshBackground
import com.example.shared.widgets.PulseButton

enum class GenerationMode {
    IMAGE, VIDEO, MUSIC
}

@Composable
fun GenerateScreen() {
    var mode by remember { mutableStateOf(GenerationMode.IMAGE) }
    var prompt by remember { mutableStateOf("") }
    
    Scaffold(
        containerColor = Midnight
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            MeshBackground()
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Generate",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Segmented Control
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = PillShape
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                        GenerationMode.values().forEach { m ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .clip(PillShape)
                                    .background(if (mode == m) SignalGradient else SolidColor(Color.Transparent))
                                    .clickable { mode = m },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = m.name.lowercase().capitalize(),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
                                    color = if (mode == m) Ink else Fog
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Prompt Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GlassCard(modifier = Modifier.weight(1f)) {
                        BasicTextField(
                            value = prompt,
                            onValueChange = { prompt = it },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Ink),
                            cursorBrush = SolidColor(Ink),
                            decorationBox = { innerTextField ->
                                Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
                                    if (prompt.isEmpty()) {
                                        Text(
                                            text = "Describe what you want...",
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
                        onClick = { /* TODO */ },
                        isActive = prompt.isNotBlank()
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // History (Placeholder)
                Text(
                    text = "Recent",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Ink
                )
            }
        }
    }
}
