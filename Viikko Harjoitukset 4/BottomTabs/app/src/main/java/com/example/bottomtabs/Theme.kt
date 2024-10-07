package com.example.bottomtabs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = MaterialTheme.colorScheme.primary,
            onPrimary = MaterialTheme.colorScheme.onPrimary,
            secondary = MaterialTheme.colorScheme.secondary,
            onSecondary = MaterialTheme.colorScheme.onSecondary,
            background = MaterialTheme.colorScheme.background,
            surface = MaterialTheme.colorScheme.surface,
            onBackground = MaterialTheme.colorScheme.onBackground,
            onSurface = MaterialTheme.colorScheme.onSurface
        ),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

private val Typography = Typography(
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp
    )
)

private val Shapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp)
)