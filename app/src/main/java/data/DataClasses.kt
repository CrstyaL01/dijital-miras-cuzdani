package com.digitalmiras.digitalmiras.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class QuickAction(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit
)

data class WalletData(
    val name: String,
    val symbol: String,
    val amount: String,
    val value: String,
    val color: Color
)

data class HeirData(
    val name: String,
    val relationship: String,
    val email: String,
    val isVerified: Boolean,
    val percentage: Int = 0
)

data class SettingItem(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)

data class TransactionData(
    val title: String,
    val amount: String,
    val time: String,
    val isIncoming: Boolean
)

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector
)

data class NotificationItem(
    val message: String,
    val time: String,
    val icon: ImageVector
)