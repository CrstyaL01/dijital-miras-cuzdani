package state

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import data.HeirData
import data.TransactionData
import data.WalletData

@Composable
fun rememberAppState(): AppState {
    return remember { AppState() }
}

class AppState {
    private val _wallets = mutableStateListOf(
        WalletData("Bitcoin Cüzdanı", "BTC", "2.45678", "$45,234.67", Color(0xFFF7931A)),
        WalletData("Ethereum Cüzdanı", "ETH", "8.9012", "$23,456.78", Color(0xFF627EEA)),
        WalletData("USDT Cüzdanı", "USDT", "15,678.90", "$15,678.90", Color(0xFF26A17B)),
        WalletData("BNB Cüzdanı", "BNB", "45.123", "$12,789.45", Color(0xFFF3BA2F))
    )
    val wallets: List<WalletData> = _wallets

    private val _heirs = mutableStateListOf(
        HeirData("Ayşe Yılmaz", "Eş", "ayse@email.com", true, 50),
        HeirData("Mehmet Yılmaz", "Oğul", "mehmet@email.com", true, 25),
        HeirData("Fatma Yılmaz", "Kız", "fatma@email.com", false, 25)
    )
    val heirs: List<HeirData> = _heirs

    private val _transactions = mutableStateListOf(
        TransactionData("Bitcoin Gönderildi", "-0.025 BTC", "2 saat önce", false),
        TransactionData("Ethereum Alındı", "+1.5 ETH", "5 saat önce", true),
        TransactionData("USDT Transfer", "-500 USDT", "1 gün önce", false),
        TransactionData("BNB Stake Ödülü", "+2.5 BNB", "2 gün önce", true),
        TransactionData("Bitcoin Alındı", "+0.15 BTC", "3 gün önce", true)
    )
    val transactions: List<TransactionData> = _transactions

    var notificationMessage by mutableStateOf("")
    var showNotification by mutableStateOf(false)

    fun addWallet(name: String, type: String) {
        val color = when (type) {
            "Bitcoin" -> Color(0xFFF7931A)
            "Ethereum" -> Color(0xFF627EEA)
            "USDT" -> Color(0xFF26A17B)
            "BNB" -> Color(0xFFF3BA2F)
            else -> Color(0xFF6B7280)
        }
        val symbol = when (type) {
            "Bitcoin" -> "BTC"
            "Ethereum" -> "ETH"
            "USDT" -> "USDT"
            "BNB" -> "BNB"
            else -> "COIN"
        }
        _wallets.add(WalletData(name, symbol, "0.00", "$0.00", color))
        showNotificationMessage("$name başarıyla eklendi!")
    }

    fun addHeir(name: String, email: String, relationship: String) {
        _heirs.add(HeirData(name, relationship, email, false, 0))
        showNotificationMessage("$name mirasçı olarak eklendi!")
    }

    fun removeHeir(heir: HeirData) {
        _heirs.remove(heir)
        showNotificationMessage("${heir.name} mirasçılardan kaldırıldı!")
    }

    fun verifyHeir(heir: HeirData) {
        val index = _heirs.indexOf(heir)
        if (index != -1) {
            _heirs[index] = heir.copy(isVerified = true)
            showNotificationMessage("${heir.name} doğrulandı!")
        }
    }

    private fun showNotificationMessage(message: String) {
        notificationMessage = message
        showNotification = true
    }

    fun hideNotification() {
        showNotification = false
    }
}