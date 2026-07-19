package com.example.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.shared.theme.DiffAdd
import com.example.shared.theme.DiffRemove
import com.example.shared.theme.Fog
import com.example.shared.theme.GlassShape
import com.example.shared.theme.Hairline
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.theme.SignalGradient
import com.example.shared.widgets.GlassCard
import com.example.shared.widgets.MeshBackground

@Composable
fun ProfileScreen() {
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
                    text = "Profile",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(GlassShape)
                            .background(SignalGradient),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "JD",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Ink
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Column {
                        Text(
                            text = "Jane Doe",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Ink
                        )
                        Text(
                            text = "Pro Plan",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Fog
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(48.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        ProfileRow("Billing")
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                        ProfileRow("Connected Accounts", value = "GitHub", valueColor = DiffAdd)
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                        ProfileRow("Notifications")
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                        ProfileRow("Help")
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    ProfileRow("Log Out", textColor = DiffRemove)
                }
            }
        }
    }
}

@Composable
private fun ProfileRow(
    title: String,
    value: String? = null,
    textColor: androidx.compose.ui.graphics.Color = Ink,
    valueColor: androidx.compose.ui.graphics.Color = Fog
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = valueColor
            )
        }
    }
}
