package com.example.features.billing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.Hairline
import com.example.shared.theme.Ink
import com.example.shared.theme.Midnight
import com.example.shared.widgets.GlassCard
import com.example.shared.widgets.MeshBackground

@Composable
fun BillingScreen() {
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
                    text = "Billing",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Next charge",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Fog
                        )
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "$20.00",
                                style = MaterialTheme.typography.headlineMedium.copy(fontFeatureSettings = "tnum"),
                                color = Ink
                            )
                            Text(
                                text = " on Oct 1",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Fog,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "History",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        HistoryRow("Sep 1", "Pro Plan", "$20.00")
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                        HistoryRow("Aug 1", "Pro Plan", "$20.00")
                    }
                }
            }
        }
    }
}

@Composable
private fun HistoryRow(date: String, plan: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = plan,
                style = MaterialTheme.typography.bodyLarge,
                color = Ink
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = Fog
            )
        }
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge.copy(fontFeatureSettings = "tnum"),
            color = Ink
        )
    }
}
