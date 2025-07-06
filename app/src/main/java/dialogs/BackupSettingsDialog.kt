package dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackupSettingsDialog(onDismiss: () -> Unit) {
    var cloudBackup by remember { mutableStateOf(true) }
    var autoBackup by remember { mutableStateOf(true) }
    var encryptBackup by remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Yedekleme Ayarları", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF1E3A8A)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Yedeklemeler şifrelenmiş olarak saklanır",
                            fontSize = 12.sp,
                            color = Color(0xFF1E3A8A)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Bulut Yedekleme", fontWeight = FontWeight.Medium)
                            Text("Google Drive'a yedekle", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = cloudBackup,
                            onCheckedChange = { cloudBackup = it }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Otomatik Yedekleme", fontWeight = FontWeight.Medium)
                            Text("Günlük otomatik yedek", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = autoBackup,
                            onCheckedChange = { autoBackup = it }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Şifrelenmiş Yedek", fontWeight = FontWeight.Medium)
                            Text("Yedekler şifrelenir", fontSize = 12.sp, color = Color(0xFF6B7280))
                        }
                        Switch(
                            checked = encryptBackup,
                            onCheckedChange = { encryptBackup = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* Manuel yedekleme */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Manuel Yedek", fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = { /* Yedek geri yükle */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Geri Yükle", fontSize = 12.sp)
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