package com.example.features.usage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.Hairline
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.theme.PillShape
import com.example.shared.theme.SignalGradient
import com.example.shared.widgets.GlassCard
import com.example.shared.widgets.MeshBackground

@Composable
fun UsageScreen() {
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
                    text = "Usage",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "14",
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontFeatureSettings = "tnum"
                                ),
                                color = Ink
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "days runway",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Fog,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                        }
                        
                        Text(
                            text = "at current pace",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Fog,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Progress Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(PillShape)
                                .background(Hairline)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(4.dp)
                                    .clip(PillShape)
                                    .background(SignalGradient)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(PillShape)
                        .border(1.dp, Hairline, PillShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Upgrade Plan",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        color = Ink
                    )
                }
            }
        }
    }
}
