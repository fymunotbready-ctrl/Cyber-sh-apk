package com.example.features.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.shared.theme.Fog
import com.example.shared.theme.GlassSurfaceElevated
import com.example.shared.theme.Ink
import com.example.shared.theme.PillShape
import com.example.shared.theme.SignalGradient
import com.example.shared.theme.springLayout
import com.example.shared.widgets.GlassCard

enum class NavItem(val icon: ImageVector) {
    LOG(Icons.Default.List),
    GENERATE(Icons.Default.PlayArrow),
    USAGE(Icons.Default.Warning),
    PROFILE(Icons.Default.Person)
}

@Composable
fun PillNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth(),
        shape = PillShape,
        surfaceColor = GlassSurfaceElevated
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem.values().forEach { item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onItemSelected(item) }
                        .animateContentSize(animationSpec = springLayout()),
                    contentAlignment = Alignment.Center
                ) {
                    val isSelected = selectedItem == item
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) SignalGradient else androidx.compose.ui.graphics.SolidColor(Color.Transparent))
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name,
                            tint = if (isSelected) Ink else Fog,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}
