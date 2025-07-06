package screens

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
import state.AppState
import data.HeirData
import dialogs.AddHeirDialog
import dialogs.HeirDetailDialog

@Composable
fun HeirsScreen(appState: AppState) {
    var showAddHeirDialog by remember { mutableStateOf(false) }
    var selectedHeir by remember { mutableStateOf<HeirData?>(null) }

    if (showAddHeirDialog) {
        AddHeirDialog(
            onDismiss = { showAddHeirDialog = false },
            onAdd = { name, email, relationship ->
                appState.addHeir(name, email, relationship)
                showAddHeirDialog = false
            }
        )
    }

    selectedHeir?.let { heir ->
        HeirDetailDialog(
            heir = heir,
            onDismiss = { selectedHeir = null },
            appState = appState
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mirasçılarım",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                    Text(
                        text = "${appState.heirs.size} kayıtlı mirasçı",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                }
                FilledIconButton(
                    onClick = { showAddHeirDialog = true },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFF1E3A8A)
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Mirasçı Ekle",
                        tint = Color.White
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFF1E3A8A),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Dijital Miras Sistemi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1E3A8A)
                        )
                        Text(
                            text = "Mirasçılarınız vefat durumunda dijital varlıklarınıza güvenle erişebilecek.",
                            fontSize = 12.sp,
                            color = Color(0xFF1E3A8A).copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }

        items(appState.heirs) { heir ->
            HeirCard(
                heir = heir,
                onClick = { selectedHeir = heir }
            )
        }
    }
}

@Composable
fun HeirCard(heir: HeirData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFF1E3A8A).copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = heir.name.split(" ").map { it.first() }.joinToString(""),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A8A)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = heir.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                Text(
                    text = "${heir.relationship} • ${heir.email}",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
                if (heir.percentage > 0) {
                    Text(
                        text = "Miras payı: %${heir.percentage}",
                        fontSize = 12.sp,
                        color = Color(0xFF1E3A8A)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (heir.isVerified) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF10B981).copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Doğrulandı",
                            fontSize = 12.sp,
                            color = Color(0xFF10B981),
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFB800).copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Bekliyor",
                            fontSize = 12.sp,
                            color = Color(0xFFFFB800),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}