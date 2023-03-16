package app.snapcart.cashier.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun UserAvatar() {
    Box(
        modifier = Modifier.size(64.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {}
}
