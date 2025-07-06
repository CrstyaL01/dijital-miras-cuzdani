package screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }

    // Logo animasyonları
    val logoScale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "logoScale"
    )

    val logoRotation by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 180f,
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
        label = "logoRotation"
    )

    // Metin animasyonları
    val titleOffsetY by animateIntAsState(
        targetValue = if (startAnimation) 0 else 100,
        animationSpec = tween(1000, delayMillis = 500, easing = FastOutSlowInEasing),
        label = "titleOffset"
    )

    val titleAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(800, delayMillis = 700),
        label = "titleAlpha"
    )

    val subtitleAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(800, delayMillis = 1000),
        label = "subtitleAlpha"
    )

    // Progress bar animasyonu
    val progressAlpha by animateFloatAsState(
        targetValue = if (showProgress) 1f else 0f,
        animationSpec = tween(400),
        label = "progressAlpha"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000)
        showProgress = true
        delay(1500)
        onSplashComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E3A8A),
                        Color(0xFF3B82F6),
                        Color(0xFF60A5FA)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = titleOffsetY.dp)
        ) {
            // Logo
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(logoScale)
                    .rotate(logoRotation)
                    .background(
                        Color.White,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    tint = Color(0xFF1E3A8A)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Ana başlık
            Text(
                text = "Digital Miras",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(titleAlpha)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Alt başlık
            Text(
                text = "Dijital Varlıklarınızın Güvenli Geleceği",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.alpha(subtitleAlpha)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Progress indicator
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(progressAlpha)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = Color.White,
                    strokeWidth = 3.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Yükleniyor...",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Decorative elements
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
        ) {
            repeat(20) { index ->
                val animatedOffset by animateFloatAsState(
                    targetValue = if (startAnimation) 1f else 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 3000 + (index * 200),
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "decorativeOffset$index"
                )

                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .offset(
                            x = (50 + index * 20).dp,
                            y = (100 + animatedOffset * 300).dp
                        )
                        .background(Color.White, CircleShape)
                )
            }
        }
    }
}