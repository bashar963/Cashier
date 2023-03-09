package app.snapcart.cashier.ui.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    labelText : String? =null,
    label : @Composable (()->Unit)? =null,
    backButtonIcon: ImageVector = Icons.Rounded.KeyboardArrowLeft,
    backButtonColor: Color = MaterialTheme.colorScheme.onPrimary,
    showBackButton: Boolean = true,
    elevation: Dp = 0.dp,
    onBackClicked : ()-> Unit,
) {

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = elevation,
    ) {
        if(showBackButton){
            IconButton(
                onClick = onBackClicked,
            ) {
                Icon(
                    modifier= Modifier.size(28.dp),
                    imageVector = backButtonIcon,
                    contentDescription ="Back button",
                    tint = backButtonColor,
                )
            }
        }
        if(label != null){
            label()
        }
        else if (labelText != null){
            Text(
                text = "$labelText",
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
            )
        }

    }
}