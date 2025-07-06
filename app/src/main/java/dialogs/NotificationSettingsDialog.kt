package dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun NotificationSettingsDialog(onDismiss: () -> Unit) {
    var pushNotifications by remember { mutableStateOf(true) }
    var emailNotifications by remember { mutableStateOf(true) }
    var transactionAlerts by remember { mutableStateOf(true) }
    var priceAlerts by remember { mutableStateOf(false) }
    var securityAlerts by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Bildirim Ayarları", fontWeight = FontWeight.Bold) },
        text = {
            LazyColumn(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Push Bildirimleri", fontWeight = FontWeight.Medium)
                            Text("Anlık bildirimler", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = pushNotifications,
                            onCheckedChange = { pushNotifications = it }
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("E-posta Bildirimleri", fontWeight = FontWeight.Medium)
                            Text("E-posta ile bildirim", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = emailNotifications,
                            onCheckedChange = { emailNotifications = it }
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("İşlem Bildirimleri", fontWeight = FontWeight.Medium)
                            Text("Transfer ve işlemler", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = transactionAlerts,
                            onCheckedChange = { transactionAlerts = it }
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Fiyat Bildirimleri", fontWeight = FontWeight.Medium)
                            Text("Fiyat değişim uyarıları", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = priceAlerts,
                            onCheckedChange = { priceAlerts = it }
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Güvenlik Bildirimleri", fontWeight = FontWeight.Medium)
                            Text("Güvenlik uyarıları", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = securityAlerts,
                            onCheckedChange = { securityAlerts = it }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Kaydet")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}