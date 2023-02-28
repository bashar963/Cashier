package app.snapcart.cashier.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// TODO(add dart color theme)
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
)

private val LightColorScheme = lightColorScheme(
        primary = PrimaryColor,
        secondary = SecondaryColor,
        tertiary = DisabledColor,
        background = BackgroundColor,
        surface = SurfaceColor,
        onPrimary = OnPrimaryColor,
        onSecondary = OnSecondaryColor,
        onBackground = OnBackgroundColor,
        onSurface = OnSurfaceColor,
        onTertiary = OnDisabledColor,
)

@Composable
fun CashierTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: throw Exception("Not in an activity - unable to get Window reference")
        SideEffect {
            currentWindow.statusBarColor =  colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = darkTheme
        }
    }


    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}