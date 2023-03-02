package app.snapcart.cashier.ui.view.auth.widgets

import androidx.compose.material3.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MainAppBar(
    onBackClicked : ()-> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = 0.dp,
    ) {
        IconButton(
            onClick = onBackClicked,
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription ="Back button",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Text(
            text = "Login or Register",
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
    }
}