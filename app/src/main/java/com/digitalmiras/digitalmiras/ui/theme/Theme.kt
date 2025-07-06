package com.digitalmiras.digitalmiras.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Gece Mavisi Renk Paleti
object NightBlueColors {
    // Ana Mavi Tonları
    val DeepNavy = Color(0xFF0A1628)           // En koyu mavi
    val MidnightBlue = Color(0xFF1E293B)       // Gece mavisi
    val DarkSlate = Color(0xFF334155)          // Koyu mavi gri
    val SlateBlue = Color(0xFF475569)          // Orta mavi gri

    // Açık Mavi Tonları
    val SkyBlue = Color(0xFF0EA5E9)            // Gökyüzü mavisi
    val LightBlue = Color(0xFF38BDF8)          // Açık mavi
    val CyanBlue = Color(0xFF06B6D4)           // Camgöbeği mavi

    // Accent Renkler
    val ElectricBlue = Color(0xFF3B82F6)       // Elektrik mavisi
    val Teal = Color(0xFF14B8A6)               // Mavi yeşil

    // Nötr Renkler
    val PureWhite = Color(0xFFFFFFFF)
    val LightGray = Color(0xFFF1F5F9)
    val MediumGray = Color(0xFF94A3B8)
    val DarkGray = Color(0xFF64748B)

    // Uyarı ve Hata Renkleri
    val Error = Color(0xFFEF4444)              // Kırmızı
}

// Açık Tema (Gündüz Modu)
private val LightNightBlueColorScheme = lightColorScheme(
    primary = NightBlueColors.MidnightBlue,
    onPrimary = NightBlueColors.PureWhite,
    primaryContainer = NightBlueColors.LightBlue,
    onPrimaryContainer = NightBlueColors.DeepNavy,

    secondary = NightBlueColors.SkyBlue,
    onSecondary = NightBlueColors.PureWhite,
    secondaryContainer = NightBlueColors.LightGray,
    onSecondaryContainer = NightBlueColors.MidnightBlue,

    tertiary = NightBlueColors.Teal,
    onTertiary = NightBlueColors.PureWhite,
    tertiaryContainer = NightBlueColors.LightGray,
    onTertiaryContainer = NightBlueColors.DarkSlate,

    background = NightBlueColors.LightGray,
    onBackground = NightBlueColors.MidnightBlue,
    surface = NightBlueColors.PureWhite,
    onSurface = NightBlueColors.MidnightBlue,
    surfaceVariant = NightBlueColors.LightGray,
    onSurfaceVariant = NightBlueColors.DarkSlate,

    error = NightBlueColors.Error,
    onError = NightBlueColors.PureWhite,
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = NightBlueColors.Error,

    outline = NightBlueColors.MediumGray,
    outlineVariant = NightBlueColors.DarkGray,
    scrim = Color(0x80000000),
    inverseSurface = NightBlueColors.MidnightBlue,
    inverseOnSurface = NightBlueColors.LightGray,
    inversePrimary = NightBlueColors.LightBlue
)

// Koyu Tema (Gece Modu)
private val DarkNightBlueColorScheme = darkColorScheme(
    primary = NightBlueColors.ElectricBlue,
    onPrimary = NightBlueColors.DeepNavy,
    primaryContainer = NightBlueColors.DarkSlate,
    onPrimaryContainer = NightBlueColors.LightBlue,

    secondary = NightBlueColors.CyanBlue,
    onSecondary = NightBlueColors.DeepNavy,
    secondaryContainer = NightBlueColors.SlateBlue,
    onSecondaryContainer = NightBlueColors.SkyBlue,

    tertiary = NightBlueColors.Teal,
    onTertiary = NightBlueColors.DeepNavy,
    tertiaryContainer = NightBlueColors.DarkSlate,
    onTertiaryContainer = NightBlueColors.Teal,

    background = NightBlueColors.DeepNavy,
    onBackground = NightBlueColors.LightGray,
    surface = NightBlueColors.MidnightBlue,
    onSurface = NightBlueColors.LightGray,
    surfaceVariant = NightBlueColors.DarkSlate,
    onSurfaceVariant = NightBlueColors.MediumGray,

    error = NightBlueColors.Error,
    onError = NightBlueColors.PureWhite,
    errorContainer = Color(0xFF2D1B1B),
    onErrorContainer = Color(0xFFFFB4AB),

    outline = NightBlueColors.DarkGray,
    outlineVariant = NightBlueColors.SlateBlue,
    scrim = Color(0xCC000000),
    inverseSurface = NightBlueColors.LightGray,
    inverseOnSurface = NightBlueColors.MidnightBlue,
    inversePrimary = NightBlueColors.MidnightBlue
)

@Composable
fun DigitalMirasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkNightBlueColorScheme
    } else {
        LightNightBlueColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

// Ek renk yardımcıları
object ThemeColors {
    @Composable
    fun primaryGradient() = listOf(
        MaterialTheme.colorScheme.primary,
        NightBlueColors.ElectricBlue,
        NightBlueColors.SkyBlue
    )

    @Composable
    fun backgroundGradient() = if (isSystemInDarkTheme()) {
        listOf(
            NightBlueColors.DeepNavy,
            NightBlueColors.MidnightBlue
        )
    } else {
        listOf(
            NightBlueColors.LightGray,
            NightBlueColors.PureWhite
        )
    }

    @Composable
    fun cardGradient() = if (isSystemInDarkTheme()) {
        listOf(
            NightBlueColors.MidnightBlue,
            NightBlueColors.DarkSlate
        )
    } else {
        listOf(
            NightBlueColors.PureWhite,
            NightBlueColors.LightGray
        )
    }
}