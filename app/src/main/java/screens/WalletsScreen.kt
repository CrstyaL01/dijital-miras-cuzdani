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
import com.digitalmiras.digitalmiras.state.AppState
import com.digitalmiras.digitalmiras.data.WalletData
import com.digitalmiras.digitalmiras.dialogs.AddWalletDialog
import com.digitalmiras.digitalmiras.dialogs.WalletDetailDialog

@Composable
fun WalletsScreen(appState: AppState) {
    var showAddWalletDialog by remember { mutableStateOf(false) }
    var selectedWallet by remember { mutableStateOf<WalletData?>(null) }

    if (showAddWalletDialog) {
        AddWalletDialog(
            onDismiss = { showAddWalletDialog = false },
            onAdd = { walletName, walletType ->
                appState.addWallet(walletName, walletType)
                showAddWalletDialog = false
            }
        )
    }

    selectedWallet?.let { wallet ->
        WalletDetailDialog(
            wallet = wallet,
            onDismiss = { selectedWallet = null },
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
                        text = "Cüzdanlarım",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                    Text(
                        text = "${appState.wallets.size} aktif cüzdan",
                        fontSize = 14.sp,
                        color = Color(0xFF6B7280)
                    )
                }
                FilledIconButton(
                    onClick = { showAddWalletDialog = true },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFF1E3A8A)
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Yeni Cüzdan",
                        tint = Color.White
                    )
                }
            }
        }

        // Toplam özet kartı
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E3A8A)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Toplam Bakiye",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "$101,659.80",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(
                            text = "↗ +12.5% bu ay",
                            color = Color(0xFF10B981),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        items(appState.wallets) { wallet ->
            WalletCard(
                wallet = wallet,
                onClick = { selectedWallet = wallet }
            )
        }
    }
}

@Composable
fun WalletCard(wallet: WalletData, onClick: () -> Unit) {
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
                    .background(wallet.color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = wallet.symbol,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = wallet.color
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = wallet.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                Text(
                    text = "${wallet.amount} ${wallet.symbol}",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = wallet.value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )
                Text(
                    text = "↗ +2.5%",
                    fontSize = 12.sp,
                    color = Color(0xFF10B981)
                )
            }
        }
    }
}