package com.example.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.features.auth.AuthScreen
import com.example.features.billing.BillingScreen
import com.example.features.generate.GenerateScreen
import com.example.features.onboarding.OnboardingScreen
import com.example.features.profile.ProfileScreen
import com.example.features.usage.UsageScreen
import com.example.shared.theme.Fog
import com.example.shared.theme.Ink
import com.example.shared.widgets.MeshBackground
import com.example.shared.widgets.PulseButton

enum class AppState {
    ONBOARDING, AUTH, MAIN
}

@Composable
fun MainApp() {
    var appState by remember { mutableStateOf(AppState.ONBOARDING) }
    
    when (appState) {
        AppState.ONBOARDING -> {
            OnboardingScreen(
                onTryIt = { appState = AppState.MAIN },
                onSignIn = { appState = AppState.AUTH }
            )
        }
        AppState.AUTH -> {
            AuthScreen(
                onSignIn = { _, _ -> appState = AppState.MAIN },
                onOAuth = { appState = AppState.MAIN }
            )
        }
        AppState.MAIN -> {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    var selectedNav by remember { mutableStateOf(NavItem.LOG) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MeshBackground()

            when (selectedNav) {
                NavItem.LOG -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Ink
                        )
                        Text(
                            text = "0 sessions",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Fog,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
                NavItem.GENERATE -> GenerateScreen()
                NavItem.USAGE -> UsageScreen()
                NavItem.PROFILE -> ProfileScreen()
            }

            PulseButton(
                onClick = { /* TODO: open bottom sheet mode picker */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 96.dp) // raised above nav bar
            )

            PillNavBar(
                selectedItem = selectedNav,
                onItemSelected = { selectedNav = it },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
