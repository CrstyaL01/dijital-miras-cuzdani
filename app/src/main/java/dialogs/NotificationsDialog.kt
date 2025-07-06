package com.digitalmiras.digitalmiras.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.digitalmiras.digitalmiras.data.NotificationItem

@Composable
fun NotificationsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Bildirimler", fontWeight = FontWeight.Bold) },
        text = {
            LazyColumn(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    listOf(
                        NotificationItem("Bitcoin fiyatı $45,000'ı aştı", "2 saat önce", Icons.Default.KeyboardArrowUp),
                        NotificationItem("Yeni mirasçı doğrulama talebi", "5 saat önce", Icons.Default.Person),
                        NotificationItem("Ethereum transfer tamamlandı", "1 gün önce", Icons.Default.CheckCircle),
                        NotificationItem("Güvenlik ayarları güncellendi", "2 gün önce", Icons.Default.Lock),
                        NotificationItem("Haftalık rapor hazır", "3 gün önce", Icons.Default.Settings)
                    )
                ) { notification ->
                    NotificationItemCard(notification)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Kapat")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                // Tümünü okundu olarak işaretle
                onDismiss()
            }) {
                Text("Tümünü Okundu İşaretle")
            }
        }
    )
}

@Composable
fun NotificationItemCard(notification: NotificationItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                notification.icon,
                contentDescription = null,
                tint = Color(0xFF1E3A8A),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    notification.message,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                Text(
                    notification.time,
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )
            }
        }
    }
}