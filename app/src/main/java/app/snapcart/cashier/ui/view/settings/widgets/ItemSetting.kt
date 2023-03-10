package app.snapcart.cashier.ui.view.settings.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemSetting(
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = title
        )
    }
}
