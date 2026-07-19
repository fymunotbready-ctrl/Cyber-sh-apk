package com.example.shared.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.Hairline
import com.example.shared.theme.Ink
import com.example.shared.theme.SignalGradient
import com.example.shared.theme.springLayout
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Finding(
    val description: String,
    val severity: String
)

data class GenerationJob(
    val id: String,
    val status: String,
    val resultUrl: String?
)

enum class Mode {
    AGENT, SEC, VIBE, CODE, CHAT
}

sealed interface RailEvent {
    val id: String
    val timestamp: Instant
    val mode: Mode
}

data class TextEvent(
    override val id: String,
    override val timestamp: Instant,
    override val mode: Mode,
    val text: String,
    val fromUser: Boolean,
) : RailEvent

data class DiffEvent(
    override val id: String,
    override val timestamp: Instant,
    override val mode: Mode,
    val filename: String,
    val removedLines: List<String>,
    val addedLines: List<String>
) : RailEvent

data class FindingsEvent(
    override val id: String,
    override val timestamp: Instant,
    override val mode: Mode,
    val findings: List<String>,
) : RailEvent

data class LogEvent(
    override val id: String,
    override val timestamp: Instant,
    override val mode: Mode,
    val logLines: List<String>,
    val cpuPct: Float,
    val memPct: Float,
    val elapsedSeconds: Int,
) : RailEvent

@Composable
fun SessionRail(
    events: List<RailEvent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        events.forEachIndexed { index, event ->
            RailItem(
                event = event,
                isLast = index == events.size - 1
            )
        }
    }
}

@Composable
private fun RailItem(
    event: RailEvent,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                if (!isLast) {
                    drawLine(
                        color = Hairline,
                        start = Offset(6.dp.toPx(), 24.dp.toPx()),
                        end = Offset(6.dp.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
            .animateContentSize(animationSpec = springLayout())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isLast) Ink else Hairline)
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = formatTime(event.timestamp),
                style = MaterialTheme.typography.labelLarge,
                color = Fog
            )
            
            Box(modifier = Modifier.padding(top = 4.dp)) {
                when (event) {
                    is TextEvent -> TextEventCard(event)
                    is DiffEvent -> DiffEventCard(event)
                    is FindingsEvent -> FindingsEventCard(event)
                    is LogEvent -> LogEventCard(event)
                }
            }
        }
    }
}

@Composable
private fun TextEventCard(event: TextEvent) {
    if (event.fromUser) {
        Text(
            text = event.text,
            style = MaterialTheme.typography.bodyLarge,
            color = Ink,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    } else {
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = 0.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(SignalGradient))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "cybersh",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = Ink
                    )
                }
                Text(
                    text = event.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Ink,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun DiffEventCard(event: DiffEvent) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.filename,
                style = MaterialTheme.typography.bodyMedium,
                color = Ink
            )
            Text(
                text = "${event.addedLines.size} additions, ${event.removedLines.size} deletions",
                style = MaterialTheme.typography.bodyMedium,
                color = Fog,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun FindingsEventCard(event: FindingsEvent) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${event.findings.size} findings",
                style = MaterialTheme.typography.bodyMedium,
                color = Ink
            )
        }
    }
}

@Composable
private fun LogEventCard(event: LogEvent) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "System Log",
                style = MaterialTheme.typography.bodyMedium,
                color = Ink
            )
        }
    }
}

private fun formatTime(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
