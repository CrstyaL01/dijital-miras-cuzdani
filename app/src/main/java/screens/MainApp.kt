package com.digitalmiras.digitalmiras.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.digitalmiras.digitalmiras.state.rememberAppState
import com.digitalmiras.digitalmiras.components.*
import com.digitalmiras.digitalmiras.dialogs.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(onLogout: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showNotificationsDialog by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }
    val tabs = listOf("Ana Sayfa", "Cüzdanlar", "Mirasçılar", "Ayarlar")
    val appState = rememberAppState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (showLogoutDialog) {
            AlertDialog(
                onDismissRequest = { showLogoutDialog = false },
                title = { Text("Çıkış Yap") },
                text = { Text("Hesabınızdan çıkış yapmak istediğinizden emin misiniz?") },
                confirmButton = {
                    TextButton(onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }) {
                        Text("Evet", color = Color(0xFFEF4444))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showLogoutDialog = false }) {
                        Text("Hayır")
                    }
                }
            )
        }

        if (showNotificationsDialog) {
            NotificationsDialog(onDismiss = { showNotificationsDialog = false })
        }

        if (showProfileDialog) {
            UserProfileDialog(onDismiss = { showProfileDialog = false })
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Digital Miras",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF1E3A8A)
                    ),
                    actions = {
                        IconButton(onClick = { showNotificationsDialog = true }) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Bildirimler",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { showProfileDialog = true }) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Profil",
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White,
                    contentColor = Color(0xFF1E3A8A)
                ) {
                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    when (index) {
                                        0 -> Icons.Default.Home
                                        1 -> Icons.Default.AccountBox
                                        2 -> Icons.Default.Person
                                        else -> Icons.Default.Settings
                                    },
                                    contentDescription = title
                                )
                            },
                            label = { Text(title, fontSize = 10.sp) },
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF1E3A8A),
                                selectedTextColor = Color(0xFF1E3A8A),
                                indicatorColor = Color(0xFFE3F2FD)
                            )
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (selectedTab) {
                    0 -> HomeScreen(appState)
                    1 -> WalletsScreen(appState)
                    2 -> HeirsScreen(appState)
                    3 -> SettingsScreen(onLogout = { showLogoutDialog = true })
                }
            }
        }

        // Notification Snackbar
        if (appState.showNotification) {
            LaunchedEffect(appState.notificationMessage) {
                delay(3000)
                appState.hideNotification()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                NotificationSnackbar(
                    message = appState.notificationMessage,
                    isVisible = appState.showNotification,
                    onDismiss = { appState.hideNotification() }
                )
            }
        }
    }
}