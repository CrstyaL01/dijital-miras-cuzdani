package com.digitalmiras.digitalmiras.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.digitalmiras.digitalmiras.state.AppState
import com.digitalmiras.digitalmiras.data.QuickAction

@Composable
fun HomeScreen(appState: AppState) {
    var showBalance by remember { mutableStateOf(true) }
    var isVisible by remember { mutableStateOf(false) }

    // Animated values
    val balanceScale by animateFloatAsState(
        targetValue = if (showBalance) 1f else 0.95f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "balanceScale"
    )

    val welcomeAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800),
        label = "welcomeAlpha"
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Animated Welcome Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(welcomeAlpha)
                    .clickable { /* Profile detayları */ },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF1E3A8A),
                                    Color(0xFF3B82F6),
                                    Color(0xFF60A5FA)
                                )
                            )
                        )
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Hoş Geldiniz,",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Ahmet Yılmaz",
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Check, // Using Check icon for security
                                    contentDescription = null,
                                    tint = Color(0xFF10B981),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Dijital varlıklarınız güvende",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 16.sp
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                appState.showNotification = true
                                appState.notificationMessage = "Veriler güncellendi!"
                            },
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.2f), CircleShape)
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Yenile",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        item {
            // Enhanced Balance Card with Animation
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(balanceScale)
                    .clickable { showBalance = !showBalance },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Toplam Varlık Değeri",
                            fontSize = 16.sp,
                            color = Color(0xFF6B7280),
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = { showBalance = !showBalance },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                if (showBalance) Icons.Default.Close else Icons.Default.Add, // Using Close/Add instead of eye icons
                                contentDescription = "Bakiyeyi göster/gizle",
                                tint = Color(0xFF6B7280),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedBalanceText(
                        balance = if (showBalance) 101659.80 else 0.0,
                        showBalance = showBalance
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF10B981).copy(alpha = 0.1f)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = Color(0xFF10B981),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "+5.23% (24s)",
                                fontSize = 14.sp,
                                color = Color(0xFF10B981),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        item {
            // Quick Actions Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hızlı İşlemler",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )
                TextButton(onClick = {
                    appState.showNotification = true
                    appState.notificationMessage = "Tüm işlemler gösteriliyor"
                }) {
                    Text(
                        "Tümünü Gör",
                        color = Color(0xFF1E3A8A),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF1E3A8A)
                    )
                }
            }
        }

        item {
            // Animated Quick Actions
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                itemsIndexed(
                    listOf(
                        QuickAction("Gönder", Icons.Default.Send, Color(0xFF3B82F6)) {
                            appState.showNotification = true
                            appState.notificationMessage = "Gönder özelliği geliştiriliyor!"
                        },
                        QuickAction("Al", Icons.Default.KeyboardArrowDown, Color(0xFF10B981)) { // Using arrow down for receive
                            appState.showNotification = true
                            appState.notificationMessage = "Al özelliği geliştiriliyor!"
                        },
                        QuickAction("QR Kod", Icons.Default.Search, Color(0xFFFFB800)) { // Using Search as QR placeholder
                            appState.showNotification = true
                            appState.notificationMessage = "QR özelliği geliştiriliyor!"
                        },
                        QuickAction("Mirasçı Ekle", Icons.Default.Add, Color(0xFF8B5CF6)) { // Using Add for adding heirs
                            appState.showNotification = true
                            appState.notificationMessage = "Mirasçı ekleme geliştiriliyor!"
                        }
                    )
                ) { index, action ->
                    AnimatedQuickActionCard(
                        action = action,
                        delay = index * 100L
                    )
                }
            }
        }

        item {
            // Recent Transactions Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Son İşlemler",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )
                TextButton(onClick = {
                    appState.showNotification = true
                    appState.notificationMessage = "Tüm işlem geçmişi açılıyor"
                }) {
                    Text(
                        "Tümünü Gör",
                        color = Color(0xFF1E3A8A),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF1E3A8A)
                    )
                }
            }
        }

        itemsIndexed(appState.transactions) { index, transaction ->
            AnimatedTransactionItem(
                transaction = transaction,
                delay = index * 50L,
                onClick = {
                    appState.showNotification = true
                    appState.notificationMessage = "İşlem detayları: ${transaction.title}"
                }
            )
        }
    }
}

@Composable
fun AnimatedBalanceText(
    balance: Double,
    showBalance: Boolean
) {
    var animatedBalance by remember { mutableStateOf(0.0) }

    val animatedValue by animateFloatAsState(
        targetValue = if (showBalance) balance.toFloat() else 0f,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "balanceAnimation"
    )

    LaunchedEffect(animatedValue) {
        animatedBalance = animatedValue.toDouble()
    }

    Text(
        text = if (showBalance) "$${String.format("%.2f", animatedBalance)}" else "••••••",
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1F2937)
    )
}

@Composable
fun AnimatedQuickActionCard(
    action: QuickAction,
    delay: Long = 0L
) {
    var isVisible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "actionScale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(400),
        label = "actionAlpha"
    )

    LaunchedEffect(Unit) {
        delay(delay)
        isVisible = true
    }

    Card(
        modifier = Modifier
            .size(120.dp)
            .scale(scale)
            .alpha(alpha)
            .clickable { action.onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                action.color.copy(alpha = 0.2f),
                                action.color.copy(alpha = 0.1f)
                            )
                        ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    action.icon,
                    contentDescription = action.title,
                    tint = action.color,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = action.title,
                fontSize = 13.sp,
                color = Color(0xFF1F2937),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun AnimatedTransactionItem(
    transaction: com.digitalmiras.digitalmiras.data.TransactionData,
    delay: Long = 0L,
    onClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    val offsetX by animateIntAsState(
        targetValue = if (isVisible) 0 else 300,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "transactionOffset"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(400),
        label = "transactionAlpha"
    )

    LaunchedEffect(Unit) {
        delay(delay)
        isVisible = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
            .alpha(alpha)
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
                    .size(48.dp)
                    .background(
                        if (transaction.isIncoming)
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF10B981).copy(alpha = 0.2f),
                                    Color(0xFF10B981).copy(alpha = 0.1f)
                                )
                            )
                        else
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFEF4444).copy(alpha = 0.2f),
                                    Color(0xFFEF4444).copy(alpha = 0.1f)
                                )
                            ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (transaction.isIncoming) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (transaction.isIncoming) Color(0xFF10B981) else Color(0xFFEF4444),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F2937)
                )
                Text(
                    text = transaction.time,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = transaction.amount,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.isIncoming) Color(0xFF10B981) else Color(0xFFEF4444)
                )
                if (transaction.isIncoming) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF10B981).copy(alpha = 0.1f)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Onaylandı",
                            fontSize = 11.sp,
                            color = Color(0xFF10B981),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}