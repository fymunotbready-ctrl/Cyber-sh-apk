package com.example.features.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun AuthScreen(
    onSignIn: (String, String) -> Unit,
    onOAuth: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Midnight
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            MeshBackground()
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                
                Text(
                    text = "cybersh",
                    style = MaterialTheme.typography.displayLarge,
                    color = Ink
                )
                
                Spacer(modifier = Modifier.height(64.dp))
                
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(1.dp)) { // 1dp for hairline
                        AuthTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Email",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                        AuthTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Password",
                            isPassword = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(PillShape)
                        .background(SignalGradient)
                        .clickable { onSignIn(email, password) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continue",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold),
                        color = Ink
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Hairline))
                
                Spacer(modifier = Modifier.height(32.dp))
                
                GhostButton(
                    text = "Continue with GitHub",
                    onClick = { onOAuth("github") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                GhostButton(
                    text = "Continue with Google",
                    onClick = { onOAuth("google") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Ink),
        cursorBrush = SolidColor(Ink),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Fog
                    )
                }
                innerTextField()
            }
        },
        modifier = modifier
    )
}

@Composable
private fun GhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(PillShape)
            .border(1.dp, Hairline, PillShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
            color = Ink
        )
    }
}
