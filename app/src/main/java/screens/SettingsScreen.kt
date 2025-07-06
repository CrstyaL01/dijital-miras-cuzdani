package com.digitalmiras.digitalmiras.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.digitalmiras.digitalmiras.data.SettingItem
import com.digitalmiras.digitalmiras.dialogs.*

@Composable
fun SettingsScreen(onLogout: () -> Unit) {
    var showUserDialog by remember { mutableStateOf(false) }
    var showSecurityDialog by remember { mutableStateOf(false) }
    var showBackupDialog by remember { mutableStateOf(false) }
    var showNotificationSettings by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var showHelpDialog by remember { mutableStateOf(false) }
    var showContactDialog by remember { mutableStateOf(false) }
    var showFeedbackDialog by remember { mutableStateOf(false) }

    if (showUserDialog) {
        UserProfileDialog(onDismiss = { showUserDialog = false })
    }
    if (showSecurityDialog) {
        SecuritySettingsDialog(onDismiss = { showSecurityDialog = false })
    }
    if (showBackupDialog) {
        BackupSettingsDialog(onDismiss = { showBackupDialog = false })
    }
    if (showNotificationSettings) {
        NotificationSettingsDialog(onDismiss = { showNotificationSettings = false })
    }
    if (showLanguageDialog) {
        LanguageSettingsDialog(onDismiss = { showLanguageDialog = false })
    }
    if (showThemeDialog) {
        ThemeSettingsDialog(onDismiss = { showThemeDialog = false })
    }
    if (showHelpDialog) {
        HelpDialog(onDismiss = { showHelpDialog = false })
    }
    if (showContactDialog) {
        ContactDialog(onDismiss = { showContactDialog = false })
    }
    if (showFeedbackDialog) {
        FeedbackDialog(onDismiss = { showFeedbackDialog = false })
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Ayarlar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1F2937)
            )
        }

        item {
            // Profil Kartı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showUserDialog = true },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFF1E3A8A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AY",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Ahmet Yılmaz",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1F2937)
                        )
                        Text(
                            text = "ahmet@email.com",
                            fontSize = 14.sp,
                            color = Color(0xFF6B7280)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF10B981).copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Premium",
                                fontSize = 10.sp,
                                color = Color(0xFF10B981),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Düzenle",
                        tint = Color(0xFF6B7280)
                    )
                }
            }
        }

        // Güvenlik Bölümü
        item {
            Text(
                text = "Güvenlik",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6B7280)
            )
        }

        items(
            listOf(
                SettingItem("Güvenlik", Icons.Default.Lock, "Biyometrik doğrulama, PIN") { showSecurityDialog = true },
                SettingItem("İki Faktörlü Doğrulama", Icons.Default.Lock, "2FA aktif") { showSecurityDialog = true },
                SettingItem("Yedekleme", Icons.Default.CheckCircle, "Otomatik yedekleme açık") { showBackupDialog = true }
            )
        ) { setting ->
            SettingItemCard(setting)
        }

        // Uygulama Bölümü
        item {
            Text(
                text = "Uygulama",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6B7280)
            )
        }

        items(
            listOf(
                SettingItem("Bildirimler", Icons.Default.Notifications, "Push bildirimleri ayarları") { showNotificationSettings = true },
                SettingItem("Dil", Icons.Default.Settings, "Türkçe") { showLanguageDialog = true },
                SettingItem("Tema", Icons.Default.Settings, "Sistem teması") { showThemeDialog = true }
            )
        ) { setting ->
            SettingItemCard(setting)
        }

        // Destek Bölümü
        item {
            Text(
                text = "Destek",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6B7280)
            )
        }

        items(
            listOf(
                SettingItem("Yardım Merkezi", Icons.Default.Info, "SSS ve rehberler") { showHelpDialog = true },
                SettingItem("İletişim", Icons.Default.Phone, "Destek ekibi") { showContactDialog = true },
                SettingItem("Geri Bildirim", Icons.Default.Settings, "Önerilerinizi paylaşın") { showFeedbackDialog = true }
            )
        ) { setting ->
            SettingItemCard(setting)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFEF4444)
                ),
                border = BorderStroke(1.dp, Color(0xFFEF4444))
            ) {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Çıkış Yap", fontWeight = FontWeight.Medium)
            }
        }

        item {
            // Uygulama bilgileri
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Digital Miras v1.0.0",
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )
                Text(
                    text = "© 2024 Digital Miras. Tüm hakları saklıdır.",
                    fontSize = 10.sp,
                    color = Color(0xFF6B7280)
                )
            }
        }
    }
}

@Composable
fun SettingItemCard(setting: SettingItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { setting.onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                setting.icon,
                contentDescription = setting.title,
                tint = Color(0xFF6B7280),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = setting.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                Text(
                    text = setting.description,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color(0xFF6B7280)
            )
        }
    }
}