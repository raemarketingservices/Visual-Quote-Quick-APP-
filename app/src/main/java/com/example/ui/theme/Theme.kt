package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
  primary = QQRed,
  onPrimary = Color.White,
  primaryContainer = QQRedLight,
  onPrimaryContainer = QQRedDark,
  secondary = QQBlue,
  onSecondary = Color.White,
  secondaryContainer = QQBlueLight,
  onSecondaryContainer = QQDarkBlue,
  tertiary = QQCyan,
  onTertiary = Color.White,
  background = QQBackground,
  onBackground = QQTextPrimary,
  surface = QQSurface,
  onSurface = QQTextPrimary,
  surfaceVariant = QQSurfaceSubtle,
  onSurfaceVariant = QQTextSecondary,
  outline = QQBorder,
  error = QQRed,
  onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
  primary = QQRed,
  onPrimary = Color.White,
  primaryContainer = QQRedDark,
  onPrimaryContainer = QQRedLight,
  secondary = QQCyan,
  onSecondary = Color.White,
  secondaryContainer = QQNavy,
  onSecondaryContainer = Color.White,
  background = QQDarkBlue,
  onBackground = Color.White,
  surface = QQNavy,
  onSurface = Color.White,
  surfaceVariant = Color(0xFF1E293B),
  onSurfaceVariant = Color(0xFFCBD5E1),
  outline = Color(0xFF334155),
  error = QQRed,
  onError = Color.White
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = false, // Default to bright clean design matching the screenshot
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      window?.statusBarColor = android.graphics.Color.TRANSPARENT
      window?.navigationBarColor = android.graphics.Color.TRANSPARENT
      if (window != null) {
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
      }
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
